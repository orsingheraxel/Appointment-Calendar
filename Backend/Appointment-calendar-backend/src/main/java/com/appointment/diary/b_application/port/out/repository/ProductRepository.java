package com.appointment.diary.b_application.port.out.repository;

import com.appointment.diary.c_domain.model.cart.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
