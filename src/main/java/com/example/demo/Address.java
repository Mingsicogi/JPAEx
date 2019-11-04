package com.example.demo;

import javax.persistence.Embeddable;

/**
 * 포함관게를 만들기 위한 VO
 *
 * @author minssogi
 */
@Embeddable
public class Address {

    private String state;

    private String city;

    private String zipCode;

    private String street;
}
