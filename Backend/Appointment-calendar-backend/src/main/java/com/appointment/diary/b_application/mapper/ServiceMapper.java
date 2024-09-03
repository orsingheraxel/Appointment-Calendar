package com.appointment.diary.b_application.mapper;

import com.appointment.diary.b_application.dto.ServiceDTO;
import com.appointment.diary.b_application.port.out.repository.UserRepository;
import com.appointment.diary.c_domain.model.BO.ServiceBO;
import com.appointment.diary.c_domain.model.Service;
import com.appointment.diary.c_domain.model.UserEntity;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ServiceMapper {

    private final UserRepository userRepository;
    private final ReviewMapper reviewMapper;
    private final AppointmentMapper appointmentMapper;
    private final PhotoMapper photoMapper;
    private final UserMapper userMapper;


    public ServiceMapper (UserRepository userRepository, @Lazy ReviewMapper reviewMapper,@Lazy AppointmentMapper appointmentMapper,@Lazy PhotoMapper photoMapper,@Lazy UserMapper userMapper){
        this.userRepository = userRepository;
        this.reviewMapper = reviewMapper;
        this.appointmentMapper = appointmentMapper;
        this.photoMapper = photoMapper;
        this.userMapper=userMapper;
    }

    public ServiceBO toBO(Service service) {
        if (service == null) {
            return null;
        }

        return ServiceBO.builder()
                .name(service.getName())
                .description(service.getDescription())
                .price(service.getPrice())
                .provider(userMapper.toBO(service.getProvider()))
                .appointments(service.getAppointments().stream().map(appointmentMapper::toBO).collect(Collectors.toSet()))
                .reviews(service.getReviews().stream().map(reviewMapper::toBO).collect(Collectors.toSet()))
                .photos(service.getPhotos().stream().map(photoMapper::toBO).collect(Collectors.toSet()))
                .build();
    }

    public ServiceDTO toDTO(Service service) {
        return ServiceDTO.builder()
                .id(service.getId())
                .name(service.getName())
                .description(service.getDescription())
                .price(service.getPrice())
                .providerId(service.getProvider().getId())
                .appointments(service.getAppointments().stream().map(appointmentMapper::toDTO).collect(Collectors.toSet()))
                .reviews(service.getReviews().stream().map(reviewMapper::toDTO).collect(Collectors.toSet()))
                .photos(service.getPhotos().stream().map(photoMapper::toDTO).collect(Collectors.toSet()))
                .build();
    }

    public Service toEntity(ServiceDTO serviceDTO) {
        Service service = new Service();
        service.setId(serviceDTO.getId());
        service.setName(serviceDTO.getName());
        service.setDescription(serviceDTO.getDescription());
        service.setPrice(serviceDTO.getPrice());
        service.setAppointments(serviceDTO.getAppointments().stream().map(appointmentMapper::toEntity).collect(Collectors.toSet()));
        service.setReviews(serviceDTO.getReviews().stream().map(reviewMapper::toEntity).collect(Collectors.toSet()));
        service.setPhotos(serviceDTO.getPhotos().stream().map(photoMapper::toEntity).collect(Collectors.toSet()));

        UserEntity provider = userRepository.findById(serviceDTO.getProviderId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        service.setProvider(provider);

        return service;
    }

    public Service toEntity(ServiceBO serviceBO){
        Service service = new Service();
        service.setName(serviceBO.getName());
        service.setDescription(serviceBO.getDescription());
        service.setPrice(serviceBO.getPrice());
        service.setAppointments(serviceBO.getAppointments().stream().map(appointmentMapper::fromBOToEntity).collect(Collectors.toSet()));
        service.setReviews(serviceBO.getReviews().stream().map(reviewMapper::toEntity).collect(Collectors.toSet()));
        service.setPhotos(serviceBO.getPhotos().stream().map(photoMapper::toEntity).collect(Collectors.toSet()));
        return service;
    }
}
