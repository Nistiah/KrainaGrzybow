package com.webpage.krainagrzybow.rdbms.models;

import com.webpage.krainagrzybow.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
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

    public boolean isProductInOrder(Long productId) {
        return orderProductsList.stream()
                .anyMatch(orderProduct -> orderProduct.getProduct().getId().equals(productId));
    }

    public OrderProduct
    getOrderProductByProductId(Long productId) {
        return orderProductsList.stream()
                .filter(orderProduct -> orderProduct.getProduct().getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Order product not found"));
    }

    public List<OrderProduct> getAllOrderProducts() {
        return orderProductsList;
    }

    public void addOrderProduct(OrderProduct orderProduct) {
        orderProductsList.add(orderProduct);
    }

    public void removeOrderProduct(OrderProduct orderProduct) {
        orderProductsList.remove(orderProduct);
    }
}

