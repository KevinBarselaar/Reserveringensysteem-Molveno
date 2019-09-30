package com.capgemini.molveno.reserveringssysteem.model;

import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;

/**
 * This class contains information about a guest. A guest is able to make a booking in the system so that
 * he/she can stay in the hotel.
 */
@EqualsAndHashCode
@Entity(name = "guest")
public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Title title;

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Date birthDate;
    private String emailAddress;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    public Guest() {
    }

    /**
     * Constructor for the Guest class.
     * @param firstName {@link String String} containing the first name of the guest
     * @param lastName {@link String String} containing the last name of the guest
     * @param phoneNumber {@link String String} containing the phone number of the guest
     * @param birthDate {@link Date Birth date} of the guest
     * @param emailAddress {@link String String} containing the email address of the guest
     * @param address {@link Address Address} of the guest
     */
    public Guest(String firstName, String lastName, String phoneNumber, Date birthDate, String emailAddress, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.emailAddress = emailAddress;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
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

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }
}
