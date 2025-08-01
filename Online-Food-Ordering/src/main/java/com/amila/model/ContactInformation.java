package com.amila.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ContactInformation {

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;

    private String email;

    private String mobile;

    private String twitter;

    private String instagram;
}
