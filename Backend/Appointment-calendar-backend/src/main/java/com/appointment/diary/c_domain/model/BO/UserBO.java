package com.appointment.diary.c_domain.model.BO;

import com.appointment.diary.c_domain.model.BO.AppointmentBO;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class UserBO {

    private Long id;
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private List<CartBO> carts;
    private List<AppointmentBO> appointments;


    // Métodos de negocio

    /*public void createCart() {
        if (hasActiveCart()) {
            throw new IllegalStateException("User already has an active cart");
        }
        CartBO newCart = new CartBO();
        carts.add(newCart);
    }

    public void addProductToCart(ProductBO product, int quantity) {
        CartBO activeCart = getActiveCart().orElseThrow(() ->
                new IllegalStateException("No active cart found")
        );
        activeCart.addProduct(product, quantity);
    }

    public void finalizeCart() {
        CartBO activeCart = getActiveCart().orElseThrow(() ->
                new IllegalStateException("No active cart to finalize")
        );
        activeCart.finalizePurchase();
    }

    // Método auxiliar para verificar si ya hay un carrito activo
    private boolean hasActiveCart() {
        return carts.stream().anyMatch(CartBO::isActive);
    }

    // Método auxiliar para obtener el carrito activo
    private Optional<CartBO> getActiveCart() {
        return carts.stream().filter(CartBO::isActive).findFirst();
    }*/

    public void updateEmail(String newEmail) {
        // Lógica de negocio para actualizar el correo electrónico
        if (newEmail == null || newEmail.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        this.email = newEmail;
    }

    public void changePassword(String oldPassword, String newPassword) {
        // Lógica de negocio para cambiar la contraseña
        if (!this.password.equals(oldPassword)) {
            throw new IllegalArgumentException("Old password is incorrect");
        }
        if (newPassword == null || newPassword.length() < 8) {
            throw new IllegalArgumentException("New password must be at least 8 characters long");
        }
        this.password = newPassword;
    }
}
