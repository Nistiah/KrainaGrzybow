package com.webpage.krainagrzybow.rdbms.models;


import com.webpage.krainagrzybow.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @NotNull
    @Column(name = "order_date")
    private LocalDate orderDate;

    @NotNull
    @OneToMany(mappedBy = "order")
    private List<OrderProduct> orderProducts;

    @NotNull
    private Status status;

}
