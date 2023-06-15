package com.webpage.krainagrzybow.rdbms.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(name = "name")
    private String name;
    @Length(max = 1000)
    @Column(name = "description")
    private String description;
    @NotNull
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "promotion")
    private BigDecimal promotion;
    @Column(name = "image")
    private String image;
    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
    private List<OrderProduct> orderProducts;
    private boolean isAvaiable = false;
    private boolean isDeleted = false;
}

