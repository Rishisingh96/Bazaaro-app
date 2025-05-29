package com.rishi.modal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String locality;
    private String address;
    private String city;
    private String state;
    private String pinCode;
    private String mobile;

    // Many addresses can belong to one user
//    @ManyToOne
//    private User user;

}
