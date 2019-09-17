package com.capgemini.molveno.reserveringssysteem.model;

import javax.persistence.*;

@Entity(name = "room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private RoomType type;
    private int adultCapacity;
    private int minorCapacity;

    @OrderColumn
    @ElementCollection(targetClass = BedType.class)
    @Enumerated(EnumType.STRING)
    private BedType[] bedTypes;

    private boolean disabledFriendly;
    private int floor;

    private double price;

    public Room() {

    }

    //TODO: remove id from constructor
    public Room(Long id, RoomType type, int adultCapacity, int minorCapacity, BedType[] bedTypes, boolean disabledFriendly, int floor, double roomPrice) {
        this.id = id;
        this.type = type;
        this.adultCapacity = adultCapacity;
        this.minorCapacity = minorCapacity;
        this.bedTypes = bedTypes;
        this.disabledFriendly = disabledFriendly;
        this.floor = floor;
        this.price = roomPrice;
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
    
}
