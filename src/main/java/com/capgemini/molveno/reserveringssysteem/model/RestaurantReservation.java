package com.capgemini.molveno.reserveringssysteem.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "reservation")
public class RestaurantReservation {

    private String lastName;
    private LocalDateTime reservationTime;
    private int numberOfGuests;
    private int numberOfMinors;

    private List<ExtraItems> extraItems = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long roomId;

    public RestaurantReservation() {
    }

    public RestaurantReservation(String lastName, LocalDateTime reservationTime, int numberOfGuests, List<ExtraItems> extraItems, int numberOfMinors, long roomId) {
        this.lastName = lastName;
        this.reservationTime = reservationTime;
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
