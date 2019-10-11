package com.capgemini.molveno.bookingsystem.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Class for displaying any extra info regarding a restaurant booking
 */
@Entity(name = "extraitems")
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

    /**
     *
     * @param disabledFriendly      {@link Boolean Boolean} to determine if the table needs to be accessible for people
     *                                                     with a handicap
     * @param isTerrace             {@link Boolean Boolean} to determine if the guests want to eat on the terrace
     * @param numberOfChildChairs   {@link Integer Number} of child chairs needed
     * @param numberOfBoosterSeats  {@link Integer Number} of booster seats needed
     */
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
