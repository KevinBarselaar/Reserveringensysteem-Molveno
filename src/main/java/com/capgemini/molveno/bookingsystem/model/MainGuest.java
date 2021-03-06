package com.capgemini.molveno.bookingsystem.model;

import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.util.Date;

/**
 * This class contains information about the main guest. A main guest is able to make a booking in the system.
 */
@Entity(name = "main_guest")
@EqualsAndHashCode(callSuper = false)
public class MainGuest extends Guest {

    private String phoneNumber;
    private String emailAddress;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    public MainGuest () {}

    /**
     * Constructor for the MainGuest
     *
     * @param title         {@link Title Prefix} for name
     * @param firstName     {@link String String} containing first name of the main guest
     * @param lastName      {@link String String} containing last name of the main guest
     * @param birthDate     {@link Date Birth date} of the main guest
     * @param phoneNumber   {@link String String} containing phone number
     * @param emailAddress  {@link String String} containing email address
     * @param address       {@link Address Object} containing address information
     */
    public MainGuest(Title title, String firstName, String lastName, Date birthDate, String phoneNumber, String emailAddress, Address address) {
        super(title, firstName, lastName, birthDate);
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
