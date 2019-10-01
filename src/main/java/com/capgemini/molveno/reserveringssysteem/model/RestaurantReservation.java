package com.capgemini.molveno.reserveringssysteem.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "reservation")
public class RestaurantReservation {

    private LocalDateTime reservationTime;
    private int numberOfGuests;
    private String extraItems;
    private int numberOfMinors;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long roomId;

    public RestaurantReservation() {
    }

    public RestaurantReservation(LocalDateTime reservationTime, int numberOfGuests, String extraItems, int numberOfMinors, long roomId) {
        this.reservationTime = reservationTime;
        this.numberOfGuests = numberOfGuests;
        this.extraItems = extraItems;
        this.numberOfMinors = numberOfMinors;
        this.roomId = roomId;
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

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }
}
