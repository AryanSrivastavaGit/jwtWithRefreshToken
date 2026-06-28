package com.aryan.jwtWithRefreshToken.service;

import com.aryan.jwtWithRefreshToken.entity.Product;
import com.aryan.jwtWithRefreshToken.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product add(Product product) {
        return productRepository.save(product);
    }

    public Optional<Product> byId(Long id) {
        return productRepository.findById(id);
    }


    public Optional<Product> updateById(Long id, Product product) {
        if(productRepository.existsById(id)){
            Optional<Product> p = productRepository.findById(id);
            p.get().setId(product.getId());
            p.get().setName(product.getName());
            p.get().setPrice(product.getPrice());
            return p;
        }
        return Optional.empty();
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
