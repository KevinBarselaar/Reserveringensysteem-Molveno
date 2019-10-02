package com.capgemini.molveno.reserveringssysteem.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "reservation")
public class RestaurantBooking {

    private String lastName;
    private LocalDateTime bookingTime;
    private int numberOfGuests;
    private int numberOfMinors;

    @OneToMany(fetch = FetchType.LAZY)
    private List<ExtraItems> extraItems = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long roomId;

    public RestaurantBooking() {
    }

    public RestaurantBooking(String lastName, LocalDateTime bookingTime, int numberOfGuests, List<ExtraItems> extraItems, int numberOfMinors, long roomId) {
        this.lastName = lastName;
        this.bookingTime = bookingTime;
        this.numberOfGuests = numberOfGuests;
        this.extraItems = extraItems;
        this.numberOfMinors = numberOfMinors;
        this.roomId = roomId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(LocalDateTime bookingTime) {
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
}
