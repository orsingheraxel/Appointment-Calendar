package com.appointment.diary.b_application.port.in;

import com.appointment.diary.b_application.command.CreateAppointmentCommand;
import com.appointment.diary.b_application.dto.AppointmentDTO;
import com.appointment.diary.b_application.mapper.AppointmentMapper;
import com.appointment.diary.b_application.mapper.ServiceMapper;
import com.appointment.diary.b_application.mapper.UserMapper;
import com.appointment.diary.b_application.port.out.repository.AppointmentRepository;
import com.appointment.diary.b_application.port.out.repository.ServiceRepository;
import com.appointment.diary.b_application.port.out.repository.UserRepository;
import com.appointment.diary.c_domain.model.Appointment;
import com.appointment.diary.c_domain.model.BO.AppointmentBO;
import com.appointment.diary.c_domain.model.BO.ServiceBO;
import com.appointment.diary.c_domain.model.BO.UserBO;
import com.appointment.diary.c_domain.model.Service;
import com.appointment.diary.c_domain.model.StatusEnum;
import com.appointment.diary.c_domain.model.UserEntity;

@org.springframework.stereotype.Service
public class CreationAppointmentUseCase {

    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;
    private final AppointmentMapper appointmentMapper;
    private final UserMapper userMapper;
    private final ServiceRepository serviceRepository;
    private final ServiceMapper serviceMapper;

    public CreationAppointmentUseCase(AppointmentRepository appointmentRepository, UserRepository userRepository, AppointmentMapper appointmentMapper, UserMapper userMapper, ServiceRepository serviceRepository, ServiceMapper serviceMapper) {
        this.appointmentRepository = appointmentRepository;
        this.userRepository = userRepository;
        this.appointmentMapper = appointmentMapper;
        this.userMapper = userMapper;
        this.serviceRepository = serviceRepository;
        this.serviceMapper=serviceMapper;
    }

    public AppointmentDTO execute(CreateAppointmentCommand command) {
        //Obtengo BO necesarios para validar reglas de negocio
        UserEntity userEntity = userRepository.findById(command.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        UserBO userBO = userMapper.toBO(userEntity);

        Service serviceEntity = serviceRepository.findById(command.getServiceId())
                .orElseThrow(() -> new IllegalArgumentException("Service not found"));

        ServiceBO serviceBO = serviceMapper.toBO(serviceEntity);

        AppointmentBO appointmentBO = AppointmentBO.builder()
                .user(userBO)
                .service(serviceBO)
                .appointmentTime(command.getAppointmentTime())
                .status(StatusEnum.PENDING)
                .build();

        //reglas de negocio
        if (!appointmentBO.validDateTime(appointmentBO.getAppointmentTime())) {
            throw new IllegalArgumentException("Invalid date or time for Appointment");
        }
        appointmentBO.confirmAppointment();

        //guardo y devuelvo entidad
        Appointment appointment = appointmentMapper.fromBOToEntity(appointmentBO);

        appointmentRepository.save(appointment);

        // Devolver el DTO para el frontend
        return appointmentMapper.toDTO(appointment);
    }
}
