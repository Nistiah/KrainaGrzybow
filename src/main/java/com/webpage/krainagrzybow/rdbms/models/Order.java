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

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

//    @NotNull
    @Column(name = "order_date")
    private LocalDate orderDate;

//    @NotNull
    @OneToMany(mappedBy = "order")
    private List<OrderProduct> orderProducts = new ArrayList<>();

//    public void addOrderProduct(OrderProduct orderProduct) {
//        if(orderProducts == null)
//            orderProducts = new ArrayList<>();
//        orderProducts.add(orderProduct);
//        orderProduct.setOrder(this);
//
//    }

    @NotNull
    private Status status;

}

