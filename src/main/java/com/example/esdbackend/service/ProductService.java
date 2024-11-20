package com.example.esdbackend.service;

import java.util.List;

import com.example.esdbackend.dto.ProductRequest;
import com.example.esdbackend.entity.Product;
import com.example.esdbackend.mapper.ProductMapper;
import com.example.esdbackend.repo.ProductRepo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepo productRepository;
    private final ProductMapper productMapper;
    public List<Product> getProductsByValueRange(double minValue, double maxValue) {

        return  productRepository.findByValue(minValue, maxValue);
    }

    public String createProduct( ProductRequest products) {
        Product product = productMapper.toProduct(products);
        productRepository.save(product);
        return "Product created";
    }
}
