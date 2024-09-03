package com.appointment.diary.b_application.mapper;


import com.appointment.diary.b_application.dto.CartProductHistoryDTO;
import com.appointment.diary.c_domain.model.BO.CartProductHistoryBO;
import com.appointment.diary.c_domain.model.cart.CartProductHistory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class CartProductHistoryMapper {

    private final ProductMapper productMapper;
    private final CartHistoryMapper cartHistoryMapper;

    public CartProductHistoryMapper(@Lazy ProductMapper productMapper,@Lazy CartHistoryMapper cartHistoryMapper) {
        this.productMapper = productMapper;
        this.cartHistoryMapper = cartHistoryMapper;
    }

    public CartProductHistoryDTO toDTO(CartProductHistoryBO cartProductHistoryBO) {
        CartProductHistoryDTO cartProductHistoryDTO = new CartProductHistoryDTO();
        cartProductHistoryDTO.setId(cartProductHistoryBO.getId());
        cartProductHistoryDTO.setCartHistoryId(cartProductHistoryBO.getCartHistory().getId());
        cartProductHistoryDTO.setProductId(cartProductHistoryBO.getProduct().getId());
        cartProductHistoryDTO.setQuantity(cartProductHistoryBO.getQuantity());
        cartProductHistoryDTO.setPriceAtPurchase(cartProductHistoryBO.getPriceAtPurchase());
        cartProductHistoryDTO.setCreatedAt(cartProductHistoryBO.getCreatedAt());
        return cartProductHistoryDTO;
    }

    public CartProductHistoryBO toBO(CartProductHistory cartProductHistoryEntity) {
        return CartProductHistoryBO.builder()
                .id(cartProductHistoryEntity.getId())
                .cartHistory(cartHistoryMapper.toBO(cartProductHistoryEntity.getCartHistory()))
                .product(productMapper.toBO(cartProductHistoryEntity.getProduct()))
                .quantity(cartProductHistoryEntity.getQuantity())
                .priceAtPurchase(cartProductHistoryEntity.getPriceAtPurchase())
                .createdAt(cartProductHistoryEntity.getCreatedAt())
                .build();
    }

    public CartProductHistory toEntity(CartProductHistoryBO cartProductHistoryBO) {
        CartProductHistory cartProductHistory = new CartProductHistory();
        cartProductHistory.setId(cartProductHistoryBO.getId());
        cartProductHistory.setCartHistory(cartHistoryMapper.toEntity(cartProductHistoryBO.getCartHistory()));
        cartProductHistory.setProduct(productMapper.toEntity(cartProductHistoryBO.getProduct()));
        cartProductHistory.setQuantity(cartProductHistoryBO.getQuantity());
        cartProductHistory.setPriceAtPurchase(cartProductHistoryBO.getPriceAtPurchase());
        cartProductHistory.setCreatedAt(cartProductHistoryBO.getCreatedAt());
        return cartProductHistory;
    }
}
