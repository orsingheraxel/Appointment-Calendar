package com.appointment.diary.b_application.mapper;

import com.appointment.diary.b_application.dto.UserDTO;
import com.appointment.diary.c_domain.model.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDTO toDTO(UserEntity userEntity) {
        return UserDTO.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .phoneNumber(userEntity.getPhoneNumber())
                .build();
    }

    public UserEntity toEntity(UserDTO userDTO) {
        return UserEntity.builder()
                .id(userDTO.getId())
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .phoneNumber(userDTO.getPhoneNumber())
                .build();
    }
}
