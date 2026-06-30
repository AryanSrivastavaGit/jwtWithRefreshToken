package com.aryan.jwtWithRefreshToken.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TokenPair {
    private String accessToken;
    private String refreshToken;
}
