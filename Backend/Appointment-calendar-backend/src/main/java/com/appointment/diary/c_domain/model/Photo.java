package com.appointment.diary.c_domain.model;

import com.appointment.diary.c_domain.model.cart.Product;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "photos")
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(nullable = false)
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id", nullable = true)
    @JsonBackReference
    private Service service;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = true)
    @JsonBackReference
    private Product product;

    @Column(nullable = false)
    private LocalDateTime uploadedAt = LocalDateTime.now();

    //Una foto esta vinculada a un servicio o a un producto, no a ambos.
    @PrePersist
    @PreUpdate
    public void validateAssociation() {
        if (service == null && product == null) {
            throw new IllegalStateException("Photo must be associated with either a Service or a Product.");
        }
        if (service != null && product != null) {
            throw new IllegalStateException("Photo cannot be associated with both a Service and a Product.");
        }
    }
}

