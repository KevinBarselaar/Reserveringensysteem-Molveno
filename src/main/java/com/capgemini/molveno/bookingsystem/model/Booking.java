package com.capgemini.molveno.bookingsystem.model;

import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * This class contains a single booking, with all the info required to link bookings to a guest.
 */
@Entity(name = "booking")
@EqualsAndHashCode
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    private List<Room> rooms;

    private String startBooking;
    private String endBooking;


    /**
     * The main booker
     */
    @OneToOne(cascade = CascadeType.ALL)
    private MainGuest mainGuest;

    /**
     * The fellow travelers
     */
    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.ALL})
    private List<Guest> guests;

    private String extraItems;

    @Enumerated(EnumType.STRING)
    private BoardType boardType;

    private LocalDateTime creationDate;

    private boolean isCheckedIn;

    public Booking() {
    }

    /**
     * Constructor for the Booking class
     *
     * @param rooms      List of {@link Room rooms} linked to the booking
     * @param extraItems {@link String String} containing any additional comments
     * @param start      {@link LocalDateTime Start date} of the booking
     * @param end        {@link LocalDateTime End date} of the booking
     */
    public Booking(List<Room> rooms, String extraItems, String start, String end) {
        this.rooms = rooms;
        this.extraItems = extraItems;
        this.startBooking = start;
        this.endBooking = end;
    }
    public Booking(List<Room> rooms, String extraItems, String start, String end, MainGuest mainGuest, List<Guest> guests) {
        this.rooms = rooms;
        this.extraItems = extraItems;
        this.startBooking = start;
        this.endBooking = end;
        this.mainGuest = mainGuest;
        this.guests = guests;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public String getStartBooking() {
        return startBooking;
    }

    public void setStartBooking(String startBooking) {
        this.startBooking = startBooking;
    }

    public String getEndBooking() {
        return endBooking;
    }

    public void setEndBooking(String endBooking) {
        this.endBooking = endBooking;
    }

    public MainGuest getMainGuest() {
        return mainGuest;
    }

    public void setMainGuest(MainGuest mainGuest) {
        this.mainGuest = mainGuest;
    }

    public List<Guest> getGuests() {
        return guests;
    }

    public void setGuests(List<Guest> guests) {
        this.guests = guests;
    }

    public String getExtraItems() {
        return extraItems;
    }

    public void setExtraItems(String extraItems) {
        this.extraItems = extraItems;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public BoardType getBoardType() {
        return boardType;
    }

    public void setBoardType(BoardType boardType) {
        this.boardType = boardType;
    }

    public boolean isCheckedIn() {
        return isCheckedIn;
    }

    public void setCheckedIn(boolean checkedIn) {
        isCheckedIn = checkedIn;
    }

    @Override
    public String toString() {
        return id + ", " + rooms + ", " + startBooking + ", " + endBooking + ", " + mainGuest + ", " + extraItems;
    }
}
