package com.capgemini.molveno.bookingsystem.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "restaurantbooking")
public class RestaurantBooking {
    private boolean isGuest;
    private String firstName;
    private String lastName;
    private String bookingDate;
    private String bookingTime;
    private int numberOfGuests;
    private int numberOfMinors;
    private long roomId;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ExtraItems> extraItems = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bookingId;



    public RestaurantBooking() {
    }

    public RestaurantBooking(boolean isGuest, String firstName, String lastName, String bookingDate, String bookingTime, int numberOfGuests, int numberOfMinors, long roomId, List<ExtraItems> extraItems, long bookingId) {
        this.isGuest = isGuest;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bookingDate = bookingDate;
        this.bookingTime = bookingTime;
        this.numberOfGuests = numberOfGuests;
        this.numberOfMinors = numberOfMinors;
        this.roomId = roomId;
        this.extraItems = extraItems;
        this.bookingId = bookingId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(String bookingTime) {
        this.bookingTime = bookingTime;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public List<ExtraItems> getExtraItems() {
        return extraItems;
    }

    public void setExtraItems(List<ExtraItems> extraItems) {
        this.extraItems = extraItems;
    }

    public int getNumberOfMinors() {
        return numberOfMinors;
    }

    public void setNumberOfMinors(int numberOfMinors) {
        this.numberOfMinors = numberOfMinors;
    }

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    public long getBookingId() {
        return bookingId;
    }

    public void setBookingId(long bookingId) {
        this.bookingId = bookingId;
    }

    public boolean isGuest() {
        return isGuest;
    }

    public void setGuest(boolean guest) {
        isGuest = guest;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }
}
