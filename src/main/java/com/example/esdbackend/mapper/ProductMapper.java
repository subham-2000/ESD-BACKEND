package com.example.esdbackend.mapper;


import com.example.esdbackend.dto.ProductRequest;
import com.example.esdbackend.dto.ProductResponse;
import com.example.esdbackend.entity.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {

    public Product toProduct(ProductRequest product) {
        return Product.builder()
                .name(product.name())
                .price(product.price())
                .build();
    }

    public ProductResponse toProductResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice()
        );
    }

}
