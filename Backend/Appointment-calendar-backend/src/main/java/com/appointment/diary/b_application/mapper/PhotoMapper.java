package com.appointment.diary.b_application.mapper;

import com.appointment.diary.b_application.dto.PhotoDTO;
import com.appointment.diary.c_domain.model.Photo;
import org.springframework.stereotype.Component;

@Component
public class PhotoMapper {

    public PhotoDTO toDTO(Photo photo) {
        return PhotoDTO.builder()
                .id(photo.getId())
                .url(photo.getUrl())
                .serviceId(photo.getService().getId())
                .providerId(photo.getProvider().getId())
                .uploadedAt(photo.getUploadedAt().toString())
                .build();
    }

    public Photo toEntity(PhotoDTO photoDTO) {
        Photo photo = new Photo();
        photo.setId(photoDTO.getId());
        photo.setUrl(photoDTO.getUrl());
        // Tendrás que asignar manualmente las entidades ServiceEntity y UserEntity desde los IDs si es necesario.
        return photo;
    }
}
