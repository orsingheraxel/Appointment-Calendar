package com.appointment.diary.b_application.mapper;

import com.appointment.diary.b_application.dto.ServiceDTO;
import com.appointment.diary.c_domain.model.Service;
import org.springframework.stereotype.Component;

@Component
public class ServiceMapper {

    public ServiceDTO toDTO(Service service) {
        return ServiceDTO.builder()
                .id(service.getId())
                .name(service.getName())
                .description(service.getDescription())
                .price(service.getPrice())
                .providerId(service.getProvider().getId())
                .build();
    }

    public Service toEntity(ServiceDTO serviceDTO) {
        Service service = new Service();
        service.setId(serviceDTO.getId());
        service.setName(serviceDTO.getName());
        service.setDescription(serviceDTO.getDescription());
        service.setPrice(serviceDTO.getPrice());
        // Tendrás que asignar manualmente la entidad UserEntity desde el ID del proveedor si es necesario.
        return service;
    }
}
