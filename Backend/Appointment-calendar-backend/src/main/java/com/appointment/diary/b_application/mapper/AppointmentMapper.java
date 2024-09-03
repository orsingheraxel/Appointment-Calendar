package com.appointment.diary.b_application.mapper;

import com.appointment.diary.b_application.dto.AppointmentDTO;
import com.appointment.diary.b_application.port.out.repository.ServiceRepository;
import com.appointment.diary.b_application.port.out.repository.UserRepository;
import com.appointment.diary.c_domain.model.Appointment;
import com.appointment.diary.c_domain.model.BO.AppointmentBO;
import com.appointment.diary.c_domain.model.Service;
import com.appointment.diary.c_domain.model.UserEntity;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class AppointmentMapper {

    private final UserMapper userMapper;
    private final ServiceMapper serviceMapper;
    private final ServiceRepository serviceRepository;
    private final UserRepository userRepository;


    public AppointmentMapper(@Lazy UserMapper userMapper,@Lazy ServiceMapper serviceMapper, ServiceRepository serviceRepository, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.serviceMapper = serviceMapper;
        this.serviceRepository=serviceRepository;
        this.userRepository = userRepository;
    }


    public AppointmentDTO toDTO(Appointment appointment) {
        return AppointmentDTO.builder()
                .id(appointment.getId())
                .userId(appointment.getUser().getId())
                .serviceId(appointment.getService().getId())
                .appointmentTime(appointment.getAppointmentTime())
                .isAnswered(appointment.isAnswered())
                .status(appointment.getStatus())
                .build();
    }

    public AppointmentBO toBO(Appointment appointment) {
        return AppointmentBO.builder()
                .id(appointment.getId())
                .user(userMapper.toBO(appointment.getUser()))
                .service(serviceMapper.toBO(appointment.getService()))
                .appointmentTime(appointment.getAppointmentTime())
                .isAnswered(appointment.isAnswered())
                .status(appointment.getStatus())
                .build();
    }

    public Appointment toEntity(AppointmentDTO appointmentDTO) {
        Appointment appointment = new Appointment();
        appointment.setId(appointmentDTO.getId());
        appointment.setAppointmentTime(appointmentDTO.getAppointmentTime());
        appointment.setAnswered(appointmentDTO.isAnswered());
        appointment.setStatus(appointmentDTO.getStatus());

        UserEntity userEntity = userRepository.findById(appointmentDTO.getUserId())
                .orElseThrow( ()-> new IllegalArgumentException("User not found"));

        Service serviceEntity = serviceRepository.findById(appointmentDTO.getServiceId())
                .orElseThrow( ()-> new IllegalArgumentException("Service not found"));

        appointment.setUser(userEntity);
        appointment.setService(serviceEntity);

        return appointment;
    }

    public Appointment fromBOToEntity(AppointmentBO appointmentBO) {
        if (appointmentBO == null) {
            return null;
        }

        Appointment appointment = new Appointment();
        appointment.setId(appointmentBO.getId());
        appointment.setUser(userMapper.toEntity(appointmentBO.getUser()));
        appointment.setService(serviceMapper.toEntity(appointmentBO.getService()));
        appointment.setAppointmentTime(appointmentBO.getAppointmentTime());
        appointment.setAnswered(appointmentBO.isAnswered());
        appointment.setStatus(appointmentBO.getStatus());

        return appointment;
    }
}
