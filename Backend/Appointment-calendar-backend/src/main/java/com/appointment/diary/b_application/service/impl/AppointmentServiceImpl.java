package com.appointment.diary.b_application.service.impl;

import com.appointment.diary.b_application.dto.AppointmentDTO;
import com.appointment.diary.b_application.mapper.AppointmentMapper;
import com.appointment.diary.b_application.mapper.ServiceMapper;
import com.appointment.diary.b_application.mapper.UserMapper;
import com.appointment.diary.b_application.port.out.repository.ServiceRepository;
import com.appointment.diary.b_application.port.out.repository.UserRepository;
import com.appointment.diary.b_application.service.AppointmentService;
import com.appointment.diary.c_domain.exception.InvalidRequestException;
import com.appointment.diary.c_domain.model.Appointment;
import com.appointment.diary.b_application.port.out.repository.AppointmentRepository;
import com.appointment.diary.c_domain.model.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentMapper appointmentMapper;
    private final AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, AppointmentMapper appointmentMapper) {
        this.appointmentRepository = appointmentRepository;
        this.appointmentMapper = appointmentMapper;
    }

    @Override
    public List<AppointmentDTO> getAllAppointments() {
        return appointmentRepository.findAll().stream()
                .map(appointmentMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<AppointmentDTO> getAppointmentById(Long id) {
        return appointmentRepository.findById(id)
                .map(appointmentMapper::toDTO);
    }

    @Override
    public AppointmentDTO createAppointment(AppointmentDTO appointmentDTO) {
        Appointment appointment = appointmentMapper.toEntity(appointmentDTO);

        Appointment savedAppointment = appointmentRepository.save(appointment);

        return appointmentMapper.toDTO(savedAppointment);
    }

    @Override
    public AppointmentDTO updateAppointment(Long id, AppointmentDTO appointmentDTO) {

        if (appointmentRepository.existsById(id)){
            Appointment appointment = appointmentMapper.toEntity(appointmentDTO);
            appointment.setId(id);
            Appointment updatedAppointment = appointmentRepository.save(appointment);
            return appointmentMapper.toDTO(updatedAppointment);
        }else{
            throw new InvalidRequestException("Appointment with ID " + id + " not found");
        }
    }

    @Override
    public void deleteAppointment(Long id) {
        if (appointmentRepository.existsById(id)){
            appointmentRepository.deleteById(id);
        }else{
            throw new InvalidRequestException("Appointment with ID " + id + " not found");
        }
    }

    @Override
    public List<AppointmentDTO> findByServiceId(Long serviceId) {
        return appointmentRepository.findByServiceId(serviceId).stream()
                .map(appointmentMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentDTO> findByUserId(Long userId) {
        return appointmentRepository.findByUserId(userId).stream()
                .map(appointmentMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void confirmAppointment(Long id) {
        if (appointmentRepository.existsById(id)) {
            appointmentRepository.confirmAppointment(id);
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }

    @Override
    public void cancelAppointment(Long id) {
        if (appointmentRepository.existsById(id)) {
            appointmentRepository.cancelAppointment(id);
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }
}
