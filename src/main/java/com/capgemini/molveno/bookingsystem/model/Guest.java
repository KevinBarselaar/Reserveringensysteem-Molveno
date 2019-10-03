package com.capgemini.molveno.bookingsystem.model;

import lombok.EqualsAndHashCode;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.Date;

/**
 * This class contains information about a guest. A guest is able to make a booking in the system so that
 * he/she can stay in the hotel.
 */
@EqualsAndHashCode
@Entity(name = "guest")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Title title;

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;
    private Date birthDate;

    public Guest() {}

    /**
     * Constructor for the Guest class.
     * @param title {@link Title Title} containing the title of the guest
     * @param firstName {@link String String} containing the first name of the guest
     * @param lastName {@link String String} containing the last name of the guest
     * @param birthDate {@link Date Birth date} of the guest
     */
    public Guest(Title title, String firstName, String lastName, Date birthDate) {
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
