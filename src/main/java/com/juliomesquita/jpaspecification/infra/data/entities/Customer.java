package com.juliomesquita.jpaspecification.infra.data.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "customer")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "customer_id", nullable = false)
    private UUID id;

    @Column(name = "customer_name")
    private String name;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders;
}
