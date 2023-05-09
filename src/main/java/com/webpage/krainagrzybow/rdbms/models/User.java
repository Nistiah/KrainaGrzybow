package com.webpage.krainagrzybow.rdbms.models;


import com.webpage.krainagrzybow.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
//    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "email")
    private String email;

    @NotNull
    @Column(name = "password")
    private String password;

    @NotNull
    @Enumerated
    @Column(name = "role")
    private Role role;


    @Column(name = "phone_number")
    private String phoneNumber;

//    @CollectionTable(name = "orders")   //??????
    @OneToMany
    @JoinColumn(name = "id", referencedColumnName = "id")
    private List<Order> orders;


}

