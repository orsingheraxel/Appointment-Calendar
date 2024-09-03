package com.appointment.diary.b_application.service;

import com.appointment.diary.b_application.dto.AppointmentDTO;
import com.appointment.diary.b_application.dto.ReviewDTO;
import com.appointment.diary.b_application.dto.UserDTO;
import com.appointment.diary.c_domain.model.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    List<UserDTO> getAllUsers();

    Optional<UserDTO> getUserById(Long id);

    UserDTO updateUser(Long id, UserDTO user);

    void deleteUser(Long id);
}
