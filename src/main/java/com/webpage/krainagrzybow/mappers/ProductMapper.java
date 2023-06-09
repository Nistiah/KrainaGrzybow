package com.webpage.krainagrzybow.mappers;

import com.webpage.krainagrzybow.dtos.ProductDto;
import com.webpage.krainagrzybow.rdbms.models.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public ProductDto mapToDto(Product product) {
        return ProductDto
                .builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .description(product.getDescription())
                .image(product.getImage())
                .build();
    }
}
