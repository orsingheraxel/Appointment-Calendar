package com.appointment.diary.b_application.service.impl;

import com.appointment.diary.b_application.dto.UserDTO;
import com.appointment.diary.b_application.mapper.UserMapper;
import com.appointment.diary.b_application.service.UserService;
import com.appointment.diary.c_domain.model.UserEntity;
import com.appointment.diary.c_domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserDTO> getUserById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toDTO);
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        if (userRepository.existsById(id)) {
            UserEntity userEntity = userMapper.toEntity(userDTO);
            userEntity.setId(id);
            return userMapper.toDTO(userRepository.save(userEntity));
        } else {
            throw new IllegalArgumentException("User with ID " + id + " not found.");
        }
    }

    @Override
    public void deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("User with ID " + id + " not found.");
        }
    }
}
