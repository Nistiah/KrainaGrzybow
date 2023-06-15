package com.webpage.krainagrzybow.rdbms.models;


import com.webpage.krainagrzybow.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "orders")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @NotNull
    @OneToMany
    @JoinColumn(name = "order_products_id")
    private List<OrderProduct> orderProductsList;


    @Column(name = "status")
    private Status status;

    @Column(name = "total_price")
    private double totalPrice;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "placed_date")
    private LocalDate date;


}

