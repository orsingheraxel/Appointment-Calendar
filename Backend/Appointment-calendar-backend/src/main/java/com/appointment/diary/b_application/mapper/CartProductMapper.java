package com.appointment.diary.b_application.mapper;

import com.appointment.diary.b_application.dto.CartProductDTO;
import com.appointment.diary.b_application.port.out.repository.CartRepository;
import com.appointment.diary.b_application.port.out.repository.ProductRepository;
import com.appointment.diary.c_domain.model.BO.CartProductBO;
import com.appointment.diary.c_domain.model.cart.Cart;
import com.appointment.diary.c_domain.model.cart.CartProduct;
import com.appointment.diary.c_domain.model.cart.Product;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class CartProductMapper {

    private final CartMapper cartMapper;
    private final ProductMapper productMapper;

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public CartProductMapper(@Lazy CartMapper cartMapper,@Lazy ProductMapper productMapper, ProductRepository productRepository, CartRepository cartRepository){
        this.cartMapper=cartMapper;
        this.productMapper=productMapper;
        this.productRepository=productRepository;
        this.cartRepository=cartRepository;
    }


    public CartProductDTO toDTO(CartProductBO cartProductBO) {
        CartProductDTO cartProductDTO = new CartProductDTO();
        cartProductDTO.setId(cartProductBO.getId());
        cartProductDTO.setCartId(cartProductBO.getCart().getId());
        cartProductDTO.setProductId(cartProductBO.getProduct().getId());
        cartProductDTO.setQuantity(cartProductBO.getQuantity());
        cartProductDTO.setAmount(cartProductBO.getAmount());
        cartProductDTO.setCreatedAt(cartProductBO.getCreatedAt());
        cartProductDTO.setUpdatedAt(cartProductBO.getUpdatedAt());
        return cartProductDTO;
    }

    public CartProductDTO toDTO(CartProduct cartProductBO) {
        CartProductDTO cartProductDTO = new CartProductDTO();
        cartProductDTO.setId(cartProductBO.getId());
        cartProductDTO.setCartId(cartProductBO.getCart().getId());
        cartProductDTO.setProductId(cartProductBO.getProduct().getId());
        cartProductDTO.setQuantity(cartProductBO.getQuantity());
        cartProductDTO.setAmount(cartProductBO.getAmount());
        cartProductDTO.setCreatedAt(cartProductBO.getCreatedAt());
        cartProductDTO.setUpdatedAt(cartProductBO.getUpdatedAt());
        return cartProductDTO;
    }

    public CartProductBO toBO(CartProduct cartProductEntity) {
        return CartProductBO.builder()
                .id(cartProductEntity.getId())
                .cart(cartMapper.toBO(cartProductEntity.getCart()))
                .product(productMapper.toBO(cartProductEntity.getProduct()))
                .quantity(cartProductEntity.getQuantity())
                .amount(cartProductEntity.getAmount())
                .createdAt(cartProductEntity.getCreatedAt())
                .updatedAt(cartProductEntity.getUpdatedAt())
                .build();
    }

    public CartProduct toEntity(CartProductBO cartProductBO) {
        CartProduct cartProduct = new CartProduct();
        cartProduct.setId(cartProductBO.getId());
        cartProduct.setCart(cartMapper.toEntity(cartProductBO.getCart()));
        cartProduct.setProduct(productMapper.toEntity(cartProductBO.getProduct()));
        cartProduct.setQuantity(cartProductBO.getQuantity());
        cartProduct.setAmount(cartProductBO.getAmount());
        cartProduct.setCreatedAt(cartProductBO.getCreatedAt());
        cartProduct.setUpdatedAt(cartProductBO.getUpdatedAt());
        return cartProduct;
    }

    public CartProduct toEntity(CartProductDTO cartProductDTO) {
        CartProduct cartProduct = new CartProduct();
        cartProduct.setId(cartProductDTO.getId());

        Cart cart = cartRepository.findById(cartProductDTO.getCartId())
                .orElseThrow(()->new IllegalArgumentException("Cart not found"));
        Product product = productRepository.findById(cartProductDTO.getProductId())
                .orElseThrow(()->new IllegalArgumentException("Cart not found"));

        cartProduct.setCart(cart);
        cartProduct.setProduct(product);

        cartProduct.setQuantity(cartProductDTO.getQuantity());
        cartProduct.setAmount(cartProductDTO.getAmount());
        cartProduct.setCreatedAt(cartProductDTO.getCreatedAt());
        cartProduct.setUpdatedAt(cartProductDTO.getUpdatedAt());
        return cartProduct;
    }
}
