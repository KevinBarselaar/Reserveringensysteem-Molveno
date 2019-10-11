package com.capgemini.molveno.bookingsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class contains information about a room. A room can be included in a {@link Booking booking} if
 * a guest adds it to his/her {@link Booking booking}.
 */
@Entity(name = "room")
@EqualsAndHashCode
@ToString
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private RoomType type;
    private int adultCapacity;
    private int minorCapacity;

    @OrderColumn
    @ElementCollection(targetClass = BedType.class)
    @Enumerated(EnumType.STRING)
    private BedType[] bedTypes;

    @JsonIgnore
    @ManyToMany(mappedBy = "rooms", cascade = {CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    private List<Booking> bookings = new ArrayList<>();

    private boolean disabledFriendly;
    private int floor;
    private double price;
    private boolean available;

    public Room() {
        this.available = true;
    }

    /**
     * Constructor for the class
     * @param type              {@link RoomType Type} of the room
     * @param adultCapacity     {@link Integer Number} containing maximum capacity of adults for the room
     * @param minorCapacity     {@link Integer Number} containing maximum capacity of minors for the room
     * @param bedTypes List of  {@link BedType types of beds} found in the room
     * @param disabledFriendly  {@link Boolean Bool} determining if disabled people can stay in the room
     * @param floor             {@link Integer Number} of the floor room
     * @param roomPrice         {@link Double Price of the room}
     */
    public Room(RoomType type, int adultCapacity, int minorCapacity, BedType[] bedTypes, boolean disabledFriendly, int floor, double roomPrice) {
        this.type = type;
        this.adultCapacity = adultCapacity;
        this.minorCapacity = minorCapacity;
        this.bedTypes = bedTypes;
        this.disabledFriendly = disabledFriendly;
        this.floor = floor;
        this.price = roomPrice;
        this.available = true;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public RoomType getType() {
        return type;
    }

    public void setType(RoomType type) {
        this.type = type;
    }

    public int getAdultCapacity() {
        return adultCapacity;
    }

    public void setAdultCapacity(int adultCapacity) {
        this.adultCapacity = adultCapacity;
    }

    public int getMinorCapacity() {
        return minorCapacity;
    }

    public void setMinorCapacity(int minorCapacity) {
        this.minorCapacity = minorCapacity;
    }

    public BedType[] getBedTypes() {
        return bedTypes;
    }

    public void setBedTypes(BedType[] bedTypes) {
        this.bedTypes = bedTypes;
    }

    public boolean isDisabledFriendly() {
        return disabledFriendly;
    }

    public void setDisabledFriendly(boolean disabledFriendly) {
        this.disabledFriendly = disabledFriendly;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
}
