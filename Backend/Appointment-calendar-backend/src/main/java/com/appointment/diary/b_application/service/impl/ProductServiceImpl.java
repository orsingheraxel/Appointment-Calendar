package com.appointment.diary.b_application.service.impl;

import com.appointment.diary.b_application.dto.ProductDTO;
import com.appointment.diary.b_application.mapper.ProductMapper;
import com.appointment.diary.b_application.port.out.repository.ProductRepository;
import com.appointment.diary.b_application.service.ProductService;
import com.appointment.diary.c_domain.model.cart.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl (ProductRepository productRepository, ProductMapper productMapper){
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }


    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProductDTO> getProductById(Long id) {
        return productRepository.findById(id)
                .map(productMapper::toDTO);
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = productMapper.toEntity(productDTO);

        Product savedProduct = productRepository.save(product);

        return productMapper.toDTO(savedProduct);
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {

        if (productRepository.existsById(id)){
            Product product = productMapper.toEntity(productDTO);
            product.setId(id);
            productRepository.save(product);
            return productMapper.toDTO(product);
        }else{
            throw new IllegalArgumentException("Product not found");
        }
    }

    @Override
    public void deleteProduct(Long id) {
        if (productRepository.existsById(id)){
            productRepository.deleteById(id);
        }else{
            throw new IllegalArgumentException("Product not found");
        }
    }
}
