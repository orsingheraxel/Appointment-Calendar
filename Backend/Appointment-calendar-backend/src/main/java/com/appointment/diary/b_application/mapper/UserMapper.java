package com.appointment.diary.b_application.mapper;

import com.appointment.diary.b_application.dto.UserDTO;
import com.appointment.diary.c_domain.model.BO.CartBO;
import com.appointment.diary.c_domain.model.BO.UserBO;
import com.appointment.diary.c_domain.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    private final CartMapper cartMapper;
    private final AppointmentMapper appointmentMapper;

    @Autowired
    public UserMapper(@Lazy CartMapper cartMapper,@Lazy AppointmentMapper appointmentMapper){
        this.cartMapper=cartMapper;
        this.appointmentMapper = appointmentMapper;
    }

    public UserDTO toDTO(UserEntity userEntity) {
        return UserDTO.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .phoneNumber(userEntity.getPhoneNumber())
                .carts(userEntity.getCarts().stream().map(cartMapper::toDTO).collect(Collectors.toList()))
                .appointments(userEntity.getAppointments().stream().map(appointmentMapper::toDTO).collect(Collectors.toList()))
                .build();
    }

    public UserEntity toEntity(UserDTO userDTO) {
        return UserEntity.builder()
                .id(userDTO.getId())
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .phoneNumber(userDTO.getPhoneNumber())
                .carts(userDTO.getCarts().stream().map(cartMapper::toEntity).collect(Collectors.toList()))
                .appointments(userDTO.getAppointments().stream().map(appointmentMapper::toEntity).collect(Collectors.toList()))
                .build();
    }

    public UserEntity toEntity(UserBO userBO){
        UserEntity userEntity = new UserEntity();
        userEntity.setName(userBO.getName());
        userEntity.setEmail(userBO.getEmail());
        userEntity.setPhoneNumber(userBO.getPhoneNumber());

        // Si el BO ya tiene un ID (caso de actualización)
        if (userBO.getId() != null) {
            userEntity.setId(userBO.getId());
        }

        // Convertir y asignar los carritos (Carts) si existen
        if (userEntity.getCarts() != null) {
            List<CartBO> cartBOs = userEntity.getCarts().stream()
                    .map(cartMapper::toBO)
                    .collect(Collectors.toList());
            userBO.setCarts(cartBOs);
        }

        return userEntity;
    }

    public UserBO toBO(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }

        return UserBO.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword()) // Cuidado al manejar el password
                .phoneNumber(userEntity.getPhoneNumber())
                .carts(userEntity.getCarts().stream().map(cartMapper::toBO).collect(Collectors.toList()))
                .appointments(userEntity.getAppointments().stream().map(appointmentMapper::toBO).collect(Collectors.toList()))
                .build();
    }
}
