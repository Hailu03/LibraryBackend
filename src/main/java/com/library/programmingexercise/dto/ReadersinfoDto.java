package com.library.programmingexercise.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReadersinfoDto {
    private Integer id;

    @JsonProperty("address")
    private String address; 

    @JsonProperty("email")
    private String email;

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("password")
    private String password;

    @JsonProperty("phoneNumber")
    private String phoneNumber;

    @JsonProperty("username")
    private String username;

    @JsonProperty("gender")
    private String gender; // Add this line

    @JsonProperty("image")
    private String image;
}
