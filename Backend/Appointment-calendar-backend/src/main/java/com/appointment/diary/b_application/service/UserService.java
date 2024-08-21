package com.appointment.diary.b_application.service;

import com.appointment.diary.b_application.dto.UserDTO;
import com.appointment.diary.c_domain.model.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserDTO> getAllUsers();

    Optional<UserDTO> getUserById(Long id);

    UserDTO updateUser(Long id, UserDTO user);

    void deleteUser(Long id);
}
