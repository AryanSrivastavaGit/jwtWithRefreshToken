package com.aryan.jwtWithRefreshToken.service;

import com.aryan.jwtWithRefreshToken.dto.LoginRequest;
import com.aryan.jwtWithRefreshToken.dto.RefreshTokenRequest;
import com.aryan.jwtWithRefreshToken.dto.RequestRegister;
import com.aryan.jwtWithRefreshToken.dto.TokenPair;
import com.aryan.jwtWithRefreshToken.entity.User;
import com.aryan.jwtWithRefreshToken.repository.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Transactional
    public void registerUser(RequestRegister requestRegister){
        if(userRepository.existsByUsername(requestRegister.getUsername())){
            throw new IllegalArgumentException("Username already exists");
        }

        User user = User.builder()
                .fullName(requestRegister.getFullName())
                .username(requestRegister.getUsername())
                .password(passwordEncoder.encode(requestRegister.getPassword()))
                .role(requestRegister.getRole())
                .build();

        userRepository.save(user);
    }

    public TokenPair login(LoginRequest loginRequest){

        //authenticate the user
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        // set authentication in security context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // generate TokenPair
        return jwtService.generateTokenPair(authentication);
    }

    public TokenPair refreshToken(@Valid RefreshTokenRequest refreshTokenRequest) {
        // check if it is a valid refresh token
        String refreshToken = refreshTokenRequest.getRefreshToken();

        if(!jwtService.isRefreshToken(refreshToken)){
            throw new IllegalArgumentException("Invalid refresh token");
        }

        String username = jwtService.extractUsername(refreshToken);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        if(userDetails == null){
            throw new IllegalArgumentException("User not found");
        }

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
        );

        String accessToken = jwtService.generateAccessToken(authentication);
        return new TokenPair(accessToken, refreshToken);
    }
}
