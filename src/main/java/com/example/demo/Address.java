package com.example.demo;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

    private String state;

    private String city;

    private String zipCode;

    private String street;
}
