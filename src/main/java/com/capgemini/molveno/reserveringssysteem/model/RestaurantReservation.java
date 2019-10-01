package com.capgemini.molveno.reserveringssysteem.model;

import java.time.LocalDateTime;

public class RestaurantReservation {

//    private Room room;
    //    private long roomId = room.getId();


    private LocalDateTime reservationTime;
    private int numberOfGuests;
    private String extraItems;
    private int numberOfMinors;

    public RestaurantReservation() {
    }

    public RestaurantReservation(LocalDateTime reservationTime, int numberOfGuests, String extraItems, int numberOfMinors) {
        this.reservationTime = reservationTime;
        this.numberOfGuests = numberOfGuests;
        this.extraItems = extraItems;
        this.numberOfMinors = numberOfMinors;
    }

    public LocalDateTime getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(LocalDateTime reservationTime) {
        this.reservationTime = reservationTime;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public String getExtraItems() {
        return extraItems;
    }

    public void setExtraItems(String extraItems) {
        this.extraItems = extraItems;
    }

    public int getNumberOfMinors() {
        return numberOfMinors;
    }

    public void setNumberOfMinors(int numberOfMinors) {
        this.numberOfMinors = numberOfMinors;
    }
}
