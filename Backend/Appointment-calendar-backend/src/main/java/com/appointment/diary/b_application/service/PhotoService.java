package com.appointment.diary.b_application.service;

import com.appointment.diary.b_application.dto.PhotoDTO;
import com.appointment.diary.c_domain.model.Photo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PhotoService {

    List<PhotoDTO> getAllPhotos();

    Optional<PhotoDTO> getPhotoById(Long id);

    PhotoDTO createPhoto(PhotoDTO photo);

    PhotoDTO updatePhoto(Long id, PhotoDTO photo);

    void deletePhoto(Long id);
}
