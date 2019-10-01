package com.capgemini.molveno.reserveringssysteem.model;

public class ExtraItems {

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
}
