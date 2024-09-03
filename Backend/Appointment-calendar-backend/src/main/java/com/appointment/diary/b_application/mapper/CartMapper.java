package com.appointment.diary.b_application.mapper;

import com.appointment.diary.b_application.dto.CartDTO;
import com.appointment.diary.b_application.port.out.repository.UserRepository;
import com.appointment.diary.c_domain.model.BO.CartBO;
import com.appointment.diary.c_domain.model.UserEntity;
import com.appointment.diary.c_domain.model.cart.Cart;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CartMapper {

    private final CartProductMapper cartProductMapper;
    private final UserMapper userMapper;

    private final UserRepository userRepository;

    public CartMapper(@Lazy CartProductMapper cartProductMapper,@Lazy UserMapper userMapper, UserRepository userRepository) {
        this.cartProductMapper = cartProductMapper;
        this.userMapper = userMapper;
        this.userRepository=userRepository;
    }

    public CartDTO toDTO(CartBO cartBO) {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setId(cartBO.getId());
        cartDTO.setUserId(cartBO.getUser().getId());
        cartDTO.setCartProducts(cartBO.getCartProducts().stream()
                .map(cartProductMapper::toDTO)
                .collect(Collectors.toList()));
        cartDTO.setCreatedAt(cartBO.getCreatedAt());
        cartDTO.setUpdatedAt(cartBO.getUpdatedAt());
        return cartDTO;
    }

    public CartDTO toDTO(Cart cartBO) {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setId(cartBO.getId());
        cartDTO.setUserId(cartBO.getUser().getId());
        cartDTO.setCartProducts(cartBO.getCartProducts().stream()
                .map(cartProductMapper::toDTO)
                .collect(Collectors.toList()));
        cartDTO.setCreatedAt(cartBO.getCreatedAt());
        cartDTO.setUpdatedAt(cartBO.getUpdatedAt());
        return cartDTO;
    }

    public CartBO toBO(Cart cartEntity) {
        return CartBO.builder()
                .id(cartEntity.getId())
                .user(userMapper.toBO(cartEntity.getUser()))
                .cartProducts(cartEntity.getCartProducts().stream().map(cartProductMapper::toBO).collect(Collectors.toList()))
                .createdAt(cartEntity.getCreatedAt())
                .updatedAt(cartEntity.getUpdatedAt())
                .build();
    }

    public Cart toEntity(CartBO cartBO) {
        Cart cart = new Cart();
        cart.setId(cartBO.getId());
        cart.setUser(userMapper.toEntity(cartBO.getUser()));
        cart.setCartProducts(cartBO.getCartProducts().stream()
                .map(cartProductMapper::toEntity)
                .collect(Collectors.toList()));
        cart.setCreatedAt(cartBO.getCreatedAt());
        cart.setUpdatedAt(cartBO.getUpdatedAt());
        return cart;
    }

    public Cart toEntity(CartDTO cartDTO) {
        Cart cart = new Cart();
        cart.setId(cartDTO.getId());

        UserEntity user = userRepository.findById(cartDTO.getUserId())
                .orElseThrow(()-> new IllegalArgumentException("User not found"));

        cart.setUser(user);
        cart.setCartProducts(cartDTO.getCartProducts().stream()
                .map(cartProductMapper::toEntity)
                .collect(Collectors.toList()));
        cart.setCreatedAt(cartDTO.getCreatedAt());
        cart.setUpdatedAt(cartDTO.getUpdatedAt());
        return cart;
    }
}
