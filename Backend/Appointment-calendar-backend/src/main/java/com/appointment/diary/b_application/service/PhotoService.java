package com.appointment.diary.b_application.service;

import com.appointment.diary.c_domain.model.Photo;

import java.util.List;
import java.util.Optional;

public interface PhotoService {

    List<Photo> getAllPhotos();

    Optional<Photo> getPhotoById(Long id);

    Photo createPhoto(Photo photo);

    Photo updatePhoto(Long id, Photo photo);

    void deletePhoto(Long id);
}
