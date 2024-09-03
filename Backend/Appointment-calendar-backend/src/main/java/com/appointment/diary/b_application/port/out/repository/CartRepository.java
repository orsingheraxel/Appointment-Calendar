package com.appointment.diary.b_application.port.out.repository;

import com.appointment.diary.c_domain.model.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
