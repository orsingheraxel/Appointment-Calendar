package com.appointment.diary.a_infraestructure.adapter.in.controller;

import com.appointment.diary.b_application.command.CreateAppointmentCommand;
import com.appointment.diary.b_application.dto.AppointmentDTO;
import com.appointment.diary.b_application.port.in.CreationAppointmentUseCase;
import com.appointment.diary.b_application.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final CreationAppointmentUseCase creationAppointmentUseCase;

    @Autowired
    public AppointmentController(AppointmentService appointmentService, CreationAppointmentUseCase creationAppointmentUseCase) {
        this.appointmentService = appointmentService;
        this.creationAppointmentUseCase = creationAppointmentUseCase;
    }

    @PostMapping("/create")
    public ResponseEntity<AppointmentDTO> CreateAppointment(@RequestBody CreateAppointmentCommand createAppointmentCommand) {
        AppointmentDTO appointmentDTO = creationAppointmentUseCase.execute(createAppointmentCommand);
        return ResponseEntity.status(HttpStatus.CREATED).body(appointmentDTO);
    }

    @GetMapping("/all")
    public ResponseEntity<List<AppointmentDTO>> GetAllAppointments() {
        List<AppointmentDTO> appointments = appointmentService.getAllAppointments();
        return ResponseEntity.ok(appointments);
    }










}
