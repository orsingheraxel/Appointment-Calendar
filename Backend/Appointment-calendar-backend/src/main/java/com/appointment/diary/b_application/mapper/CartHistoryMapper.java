package com.appointment.diary.b_application.mapper;

import com.appointment.diary.b_application.dto.CartHistoryDTO;
import com.appointment.diary.c_domain.model.BO.CartHistoryBO;
import com.appointment.diary.c_domain.model.cart.CartHistory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CartHistoryMapper {

    private final CartProductHistoryMapper cartProductHistoryMapper;
    private final UserMapper userMapper;

    public CartHistoryMapper(@Lazy CartProductHistoryMapper cartProductHistoryMapper,@Lazy UserMapper userMapper) {
        this.cartProductHistoryMapper = cartProductHistoryMapper;
        this.userMapper = userMapper;
    }

    public CartHistoryDTO toDTO(CartHistoryBO cartHistoryBO) {
        CartHistoryDTO cartHistoryDTO = new CartHistoryDTO();
        cartHistoryDTO.setId(cartHistoryBO.getId());
        cartHistoryDTO.setUserId(cartHistoryBO.getUser().getId());
        cartHistoryDTO.setTotalPrice(cartHistoryBO.getTotalPrice());
        cartHistoryDTO.setCartProductHistories(cartHistoryBO.getCartProductHistories().stream()
                .map(cartProductHistoryMapper::toDTO)
                .collect(Collectors.toList()));
        cartHistoryDTO.setCreatedAt(cartHistoryBO.getCreatedAt());
        return cartHistoryDTO;
    }

    public CartHistoryBO toBO(CartHistory cartHistoryEntity) {
        return CartHistoryBO.builder()
                        .id(cartHistoryEntity.getId())
                .user(userMapper.toBO(cartHistoryEntity.getUser()))
                .cartProductHistories(cartHistoryEntity.getCartProductHistories().stream().map(cartProductHistoryMapper::toBO).collect(Collectors.toList()))
                .totalPrice(cartHistoryEntity.getTotalPrice())
                .createdAt(cartHistoryEntity.getCreatedAt())
                .build();
    }

    public CartHistory toEntity(CartHistoryBO cartHistoryBO) {
        CartHistory cartHistory = new CartHistory();
        cartHistory.setId(cartHistoryBO.getId());
        cartHistory.setUser(userMapper.toEntity(cartHistoryBO.getUser()));
        cartHistory.setTotalPrice(cartHistoryBO.getTotalPrice());
        cartHistory.setCartProductHistories(cartHistoryBO.getCartProductHistories().stream()
                .map(cartProductHistoryMapper::toEntity)
                .collect(Collectors.toList()));
        cartHistory.setCreatedAt(cartHistoryBO.getCreatedAt());
        return cartHistory;
    }
}
