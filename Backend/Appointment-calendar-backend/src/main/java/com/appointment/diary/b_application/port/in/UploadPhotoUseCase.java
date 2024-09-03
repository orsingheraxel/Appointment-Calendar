package com.appointment.diary.b_application.port.in;

import com.appointment.diary.b_application.dto.PhotoDTO;
import com.appointment.diary.b_application.mapper.PhotoMapper;
import com.appointment.diary.b_application.port.out.repository.PhotoRepository;
import com.appointment.diary.b_application.port.out.repository.ProductRepository;
import com.appointment.diary.b_application.port.out.repository.ServiceRepository;
import com.appointment.diary.c_domain.model.Photo;
import com.appointment.diary.c_domain.model.Service;
import com.appointment.diary.c_domain.model.cart.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

@org.springframework.stereotype.Service
public class UploadPhotoUseCase {

    @Value("${photo.upload.dir}")
    private String uploadDir;

    private final PhotoRepository photoRepository;
    private final PhotoMapper photoMapper;
    private final ServiceRepository serviceRepository;
    private final ProductRepository productRepository;

    public UploadPhotoUseCase(PhotoRepository photoRepository, ServiceRepository serviceRepository, ProductRepository productRepository, PhotoMapper photoMapper) {
        this.photoRepository = photoRepository;
        this.serviceRepository = serviceRepository;
        this.productRepository = productRepository;
        this.photoMapper = photoMapper;
    }

    public PhotoDTO uploadPhoto(MultipartFile file, Long serviceId, Long productId) throws IOException {
        //valido que se le haya asignado un ID foraneo
        if ((serviceId == null && productId == null) || (serviceId != null && productId != null)) {
            throw new IllegalArgumentException("You must provide either a serviceId or a productId, but not both.");
        }

        //genero nombre de la foto
        String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path filepath = Paths.get(uploadDir, filename);



        Photo photo = new Photo();
        photo.setUrl(filepath.toString());
        photo.setUploadedAt(LocalDateTime.now());

        //asigno la relación con Service o Product
        if (serviceId != null) {
            Service service = serviceRepository.findById(serviceId)
                    .orElseThrow(() -> new IllegalArgumentException("Service not found"));
            photo.setService(service);
        }else{
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new IllegalArgumentException("Product not found"));
            photo.setProduct(product);
        }

        photoRepository.save(photo);

        return photoMapper.toDTO(photo);
    }
}
