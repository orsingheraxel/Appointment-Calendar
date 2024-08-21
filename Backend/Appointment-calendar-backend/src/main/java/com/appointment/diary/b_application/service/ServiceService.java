package com.appointment.diary.b_application.service;

import com.appointment.diary.c_domain.model.Service;

import java.util.List;
import java.util.Optional;

public interface ServiceService {

    List<Service> getAllServices();

    Optional<Service> getServiceById(Long id);

    Service createService(Service service);

    Service updateService(Long id, Service service);

    void deleteService(Long id);
}
