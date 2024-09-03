package com.appointment.diary.b_application.service.impl;

import com.appointment.diary.b_application.dto.PhotoDTO;
import com.appointment.diary.b_application.mapper.PhotoMapper;
import com.appointment.diary.b_application.service.PhotoService;
import com.appointment.diary.c_domain.exception.InvalidRequestException;
import com.appointment.diary.c_domain.model.Photo;
import com.appointment.diary.b_application.port.out.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PhotoServiceImpl implements PhotoService {

    private final PhotoMapper photoMapper;
    private final PhotoRepository photoRepository;

    @Autowired
    public PhotoServiceImpl(PhotoRepository photoRepository, PhotoMapper photoMapper) {
        this.photoRepository = photoRepository;
        this.photoMapper = photoMapper;
    }

    @Override
    public List<PhotoDTO> getAllPhotos() {
        return photoRepository.findAll().stream()
                .map(photoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PhotoDTO> getPhotoById(Long id) {
        return photoRepository.findById(id)
                .map(photoMapper::toDTO);
    }

    @Override
    public PhotoDTO createPhoto(PhotoDTO photoDTO) {
        Photo photo = photoMapper.toEntity(photoDTO);

        Photo savedphoto = photoRepository.save(photo);

        return photoMapper.toDTO(savedphoto);
    }

    @Override
    public PhotoDTO updatePhoto(Long id, PhotoDTO photoDTO) {

        if (photoRepository.existsById(id)){
            Photo photo = photoMapper.toEntity(photoDTO);
            photo.setId(id);
            return photoMapper.toDTO(photo);

        }else{
            throw new InvalidRequestException("Photo with ID " + id + " not found");
        }
    }

    @Override
    public void deletePhoto(Long id) {
        if (photoRepository.existsById(id)){
            photoRepository.deleteById(id);
        }else{
            throw new InvalidRequestException("Photo with ID " + id + " not found");
        }
    }
}
