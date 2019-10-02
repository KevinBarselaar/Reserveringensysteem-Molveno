package com.capgemini.molveno.bookingsystem.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Class where the address of a guest is stored.
 */
@Entity(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String streetName;
    private int houseNumber;
    private String houseNumberAddition;
    private String postalCode;
    private String city;
    private String country;

    /**
     * Constructor for address
     * @param streetName {@link String String} containing address
     * @param houseNumber {@link Integer Integer} containing house number
     * @param houseNumberAddition {@link String String} containing addition to house number
     *                                                 (e.g. "B" if complete house number is "2B")
     * @param postalCode {@link String String} containing zip code
     * @param city {@link String String} containing city
     * @param country {@link String String} containing country
     */
    public Address(String streetName, int houseNumber, String houseNumberAddition, String postalCode, String city, String country) {
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.houseNumberAddition = houseNumberAddition;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
    }

    public Address() {

    }

    public String getStreetName() {
        return this.streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getHouseNumberAddition() {
        return houseNumberAddition;
    }

    public void setHouseNumberAddition(String houseNumberAddition) {
        this.houseNumberAddition = houseNumberAddition;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
