package com.capgemini.molveno.reserveringssysteem.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "extra")
public class ExtraItems {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private boolean disabledFriendly;
    private boolean isTerrace;
    private int numberOfChildChairs;
    private int numberOfBoosterSeats;


    public ExtraItems() {
    }

    public ExtraItems(boolean disabledFriendly, boolean isTerrace, int numberOfChildChairs, int numberOfBoosterSeats) {
        this.disabledFriendly = disabledFriendly;
        this.isTerrace = isTerrace;
        this.numberOfChildChairs = numberOfChildChairs;
        this.numberOfBoosterSeats = numberOfBoosterSeats;
    }

    public boolean isDisabledFriendly() {
        return disabledFriendly;
    }

    public void setDisabledFriendly(boolean disabledFriendly) {
        this.disabledFriendly = disabledFriendly;
    }

    public boolean isTerrace() {
        return isTerrace;
    }

    public void setTerrace(boolean terrace) {
        isTerrace = terrace;
    }

    public int getNumberOfChildChairs() {
        return numberOfChildChairs;
    }

    public void setNumberOfChildChairs(int numberOfChildChairs) {
        this.numberOfChildChairs = numberOfChildChairs;
    }

    public int getNumberOfBoosterSeats() {
        return numberOfBoosterSeats;
    }

    public void setNumberOfBoosterSeats(int numberOfBoosterSeats) {
        this.numberOfBoosterSeats = numberOfBoosterSeats;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
