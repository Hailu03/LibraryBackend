package com.library.programmingexercise.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "readersinfo")


public class ReadersInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "readerID", nullable = false)
    private Integer id;

    @Column(name = "address", nullable = false, length = 1000)
    private String address;

    @Column(name = "email", nullable = false, length = 1000)
    private String email;

    @Column(name = "first_name", nullable = false, length = 1000)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 1000)
    private String lastName;

    @Column(name = "password", nullable = false, length = 1000)
    private String password;

    @Column(name = "phone_number", nullable = false, length = 1000)
    private String phoneNumber;

    @Column(name = "username", nullable = false, length = 1000)
    private String username;

    @Column(name = "gender", length = 10)
    private String gender;

    @Column(name = "image", nullable = false)
    private byte[] image;

}