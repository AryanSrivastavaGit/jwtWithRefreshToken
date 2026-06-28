package com.aryan.jwtWithRefreshToken.controller;

import com.aryan.jwtWithRefreshToken.entity.Product;
import com.aryan.jwtWithRefreshToken.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("getAll")
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<>(productService.getAll(), HttpStatus.NOT_FOUND);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> byId(@PathVariable Long id){
        return new ResponseEntity<>(productService.byId(id), HttpStatus.NOT_FOUND);
    }

    @PostMapping("add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> add(@Valid @RequestBody Product product){
        return new ResponseEntity<>(productService.add(product), HttpStatus.NOT_FOUND);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> updateById(@PathVariable Long id, @Valid @RequestBody Product product){
        return new ResponseEntity<>(productService.updateById(id, product), HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        productService.deleteById(id);
        return new ResponseEntity<>("Product Deleted Successfully", HttpStatus.OK);
    }
}
