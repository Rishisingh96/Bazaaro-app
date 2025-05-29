package com.rishi.modal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.rishi.domain.USER_ROLE;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private String email;

    private String fullName;

    private String mobile;


    private USER_ROLE role = USER_ROLE.ROLE_CUSTOMER;

    @OneToMany
    private Set<Address> addresses = new HashSet<>();
// One user can have many addresses
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Address> addresses = new ArrayList<>();


    @ManyToMany
    @JsonIgnore
    private Set<Coupon> usedCoupons = new HashSet<>();

}
