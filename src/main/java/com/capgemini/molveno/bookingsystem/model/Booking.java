package com.capgemini.molveno.bookingsystem.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * This class contains a single hotel booking, with all the info required to link an hotel booking to a guest.
 */
@Entity(name = "booking")
@EqualsAndHashCode
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    private List<Room> rooms;

    @JsonFormat(pattern="dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    private Date startBooking;

    @JsonFormat(pattern="dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    private Date endBooking;

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
    private String preference;

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
     * @param start      {@link Date Start date} of the booking
     * @param end        {@link Date End date} of the booking
     */
    public Booking(List<Room> rooms, String extraItems, Date start, Date end) {
        this.rooms = rooms;
        this.extraItems = extraItems;
        this.startBooking = start;
        this.endBooking = end;
    }

    /**
     * Constructor for the Booking class
     *
     * @param rooms         List of {@link Room rooms} linked to the booking
     * @param extraItems    {@link String String} containing any additional comments
     * @param start         {@link Date Start date} of the booking
     * @param end           {@link Date End date} of the booking
     * @param mainGuest     {@link MainGuest Main guest} of the booking
     * @param guests        List of {@link Guest guests} accompanying the main guest
     * @param preference    {@link String String} containing {@link Room room preference}
     */
    public Booking(List<Room> rooms, String extraItems, Date start, Date end, MainGuest mainGuest, List<Guest> guests, String preference) {
        this.rooms = rooms;
        this.extraItems = extraItems;
        this.startBooking = start;
        this.endBooking = end;
        this.mainGuest = mainGuest;
        this.guests = guests;
        this.preference = preference;
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

    public Date getStartBooking() {
        return startBooking;
    }

    public void setStartBooking(Date startBooking) {
        this.startBooking = startBooking;
    }

    public Date getEndBooking() {
        return endBooking;
    }

    public void setEndBooking(Date endBooking) {
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

    public String getPreference() {
        return preference;
    }

    public void setPreference(String preference) {
        this.preference = preference;
    }

    @Override
    public String toString() {
        return id + ", " + rooms + ", " + startBooking + ", " + endBooking + ", " + mainGuest + ", " + extraItems;
    }
}
