package com.appointment.diary.b_application.mapper;

import com.appointment.diary.b_application.dto.PhotoDTO;
import com.appointment.diary.c_domain.model.BO.PhotoBO;
import com.appointment.diary.b_application.port.out.repository.ProductRepository;
import com.appointment.diary.b_application.port.out.repository.ServiceRepository;
import com.appointment.diary.c_domain.model.Photo;
import com.appointment.diary.c_domain.model.Service;
import com.appointment.diary.c_domain.model.cart.Product;
import org.springframework.stereotype.Component;

@Component
public class PhotoMapper {

    private final ServiceRepository serviceRepository;
    private final ProductRepository productRepository;

    public PhotoMapper(ServiceRepository serviceRepository, ProductRepository productRepository) {
        this.serviceRepository = serviceRepository;
        this.productRepository = productRepository;
    }

    public PhotoDTO toDTO(PhotoBO photoBO) {
        return PhotoDTO.builder()
                .id(photoBO.getId())
                .url(photoBO.getUrl())
                .serviceId(photoBO.getServiceId())
                .productId(photoBO.getProductId())
                .uploadedAt(photoBO.getUploadedAt())
                .build();
    }

    public PhotoBO toBO(Photo photo) {
        return PhotoBO.builder()
                .id(photo.getId())
                .url(photo.getUrl())
                .serviceId(photo.getService() != null ? photo.getService().getId() : null)
                .productId(photo.getProduct() != null ? photo.getProduct().getId() : null)
                .uploadedAt(photo.getUploadedAt())
                .build();
    }

    public Photo toEntity(PhotoDTO photoDTO) {
        Photo photo = new Photo();
        photo.setId(photoDTO.getId());
        photo.setUrl(photoDTO.getUrl());

        if (photoDTO.getServiceId() != null) {
            Service service = serviceRepository.findById(photoDTO.getServiceId())
                    .orElseThrow(() -> new IllegalArgumentException("Service not found"));
            photo.setService(service);
        }

        if (photoDTO.getProductId() != null) {
            Product product = productRepository.findById(photoDTO.getProductId())
                    .orElseThrow(() -> new IllegalArgumentException("Product not found"));
            photo.setProduct(product);
        }

        photo.setUploadedAt(photoDTO.getUploadedAt());
        return photo;
    }

    public PhotoDTO toDTO(Photo photo) {
        return PhotoDTO.builder()
                .id(photo.getId())
                .url(photo.getUrl())
                .serviceId(photo.getService() != null ? photo.getService().getId() : null)
                .productId(photo.getProduct() != null ? photo.getProduct().getId() : null)
                .uploadedAt(photo.getUploadedAt())
                .build();
    }

    public Photo toEntity(PhotoBO photoBO) {
        Photo photo = new Photo();
        photo.setId(photoBO.getId());
        photo.setUrl(photoBO.getUrl());

        if (photoBO.getServiceId() != null) {
            Service service = serviceRepository.findById(photoBO.getServiceId())
                    .orElseThrow(() -> new IllegalArgumentException("Service not found"));
            photo.setService(service);
        }

        if (photoBO.getProductId() != null) {
            Product product = productRepository.findById(photoBO.getProductId())
                    .orElseThrow(() -> new IllegalArgumentException("Product not found"));
            photo.setProduct(product);
        }

        photo.setUploadedAt(photoBO.getUploadedAt());
        return photo;
    }

}
