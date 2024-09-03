package com.appointment.diary.b_application.service;

import com.appointment.diary.b_application.dto.ServiceDTO;
import com.appointment.diary.c_domain.model.Service;

import java.util.List;
import java.util.Optional;

public interface ServiceService {

    List<ServiceDTO> getAllServices();

    Optional<ServiceDTO> getServiceById(Long id);

    ServiceDTO createService(ServiceDTO service);

    ServiceDTO updateService(Long id, ServiceDTO service);

    void deleteService(Long id);
}
