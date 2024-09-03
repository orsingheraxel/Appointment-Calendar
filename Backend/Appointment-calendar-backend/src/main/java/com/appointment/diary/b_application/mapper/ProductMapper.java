package com.appointment.diary.b_application.mapper;

import com.appointment.diary.c_domain.model.BO.ProductBO;
import com.appointment.diary.b_application.dto.ProductDTO;
import com.appointment.diary.c_domain.model.cart.Product;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ProductMapper {

    private final PhotoMapper photoMapper;

    public ProductMapper(@Lazy PhotoMapper photoMapper){
        this.photoMapper=photoMapper;
    }

    public ProductDTO toDTO(ProductBO productBO) {
        return ProductDTO.builder()
                .id(productBO.getId())
                .name(productBO.getName())
                .description(productBO.getDescription())
                .price(productBO.getPrice())
                .stockQuantity(productBO.getStockQuantity())
                .category(productBO.getCategory())
                .photos(productBO.getPhotos().stream().map(photoMapper::toDTO).collect(Collectors.toSet()))
                .build();
    }

    public ProductDTO toDTO(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stockQuantity(product.getStockQuantity())
                .category(product.getCategory())
                .photos(product.getPhotos().stream().map(photoMapper::toDTO).collect(Collectors.toSet()))
                .build();
    }

    public Product toEntity(ProductDTO productDTO) {
        return Product.builder()
                .id(productDTO.getId())
                .name(productDTO.getName())
                .description(productDTO.getDescription())
                .price(productDTO.getPrice())
                .stockQuantity(productDTO.getStockQuantity())
                .category(productDTO.getCategory())
                .photos(productDTO.getPhotos().stream().map(photoMapper::toEntity).collect(Collectors.toSet()))
                .build();
    }

    public ProductBO toBO(Product product) {
        return ProductBO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stockQuantity(product.getStockQuantity())
                .category(product.getCategory())
                .photos(product.getPhotos().stream().map(photoMapper::toBO).collect(Collectors.toSet()))
                .build();
    }

    public Product toEntity(ProductBO productBO) {
        return Product.builder()
                .id(productBO.getId())
                .name(productBO.getName())
                .description(productBO.getDescription())
                .price(productBO.getPrice())
                .stockQuantity(productBO.getStockQuantity())
                .category(productBO.getCategory())
                .photos(productBO.getPhotos().stream().map(photoMapper::toEntity).collect(Collectors.toSet()))
                .build();
    }
}
