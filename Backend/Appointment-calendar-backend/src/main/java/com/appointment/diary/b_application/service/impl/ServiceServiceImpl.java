package com.appointment.diary.b_application.service.impl;

import com.appointment.diary.b_application.dto.ServiceDTO;
import com.appointment.diary.b_application.mapper.ServiceMapper;
import com.appointment.diary.b_application.service.ServiceService;
import com.appointment.diary.c_domain.exception.InvalidRequestException;
import com.appointment.diary.c_domain.model.Service;
import com.appointment.diary.b_application.port.out.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService {
    
    private final ServiceMapper serviceMapper;
    private final ServiceRepository serviceRepository;

    @Autowired
    public ServiceServiceImpl(ServiceRepository serviceRepository, ServiceMapper serviceMapper) {
        this.serviceRepository = serviceRepository;
        this.serviceMapper = serviceMapper;
    }

    @Override
    public List<ServiceDTO> getAllServices() {
        return serviceRepository.findAll().stream()
                .map(serviceMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ServiceDTO> getServiceById(Long id) {
        return serviceRepository.findById(id)
                .map(serviceMapper::toDTO);
    }

    @Override
    public ServiceDTO createService(ServiceDTO serviceDTO) {
        Service service = serviceMapper.toEntity(serviceDTO);

        Service savedservice = serviceRepository.save(service);

        return serviceMapper.toDTO(savedservice);
    }

    @Override
    public ServiceDTO updateService(Long id, ServiceDTO serviceDTO) {

        if (serviceRepository.existsById(id)){
            Service service = serviceMapper.toEntity(serviceDTO);
            service.setId(id);
            return serviceMapper.toDTO(service);
        }else{
            throw new InvalidRequestException("Service with ID " + id + " not found");
        }
    }

    @Override
    public void deleteService(Long id) {
        if (serviceRepository.existsById(id)){
            serviceRepository.deleteById(id);
        }else{
            throw new InvalidRequestException("Service with ID " + id + " not found");
        }
    }
}
