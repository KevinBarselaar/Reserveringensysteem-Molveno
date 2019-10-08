package com.capgemini.molveno.bookingsystem.services;

import com.capgemini.molveno.bookingsystem.model.Booking;
import com.capgemini.molveno.bookingsystem.model.Room;
import com.capgemini.molveno.bookingsystem.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomService {

    private final RoomRepository repository;

    @Autowired
    public RoomService(RoomRepository repository) {
        this.repository = repository;
    }

    public List<Room> findAll() {
        return this.repository.findAll();
    }

    public Room findById(Long id) {
        return this.repository.findById(id).get(); //TODO throw not found exception if not found
    }

    public List<Room> findAllAvailable() {
        return this.repository.findAllAvailable();
    }

    public List<Room> findAllAvailableBetweenDates(Date startDate, Date endDate) {
        return this.findAll()
                .stream()
                .filter(room -> !isRoomBookedBetweenDates(room, startDate, endDate))
                .collect(Collectors.toList());
    }

    public Room create(Room room) {
        return this.repository.saveAndFlush(room);
    }

    public void deleteById(Long id) {
        this.repository.deleteById(id);
    }

    private boolean isRoomBookedBetweenDates(Room room, Date startDate, Date endDate) {
        for (Booking booking : room.getBookings()) {
            if (dateGreatherThanOrEqualTo(startDate, booking.getStartBooking()) && dateLessThanOrEqualTo(endDate, booking.getEndBooking())) {
                return true;
            }
        }

        return false;
    }

    private boolean dateGreatherThanOrEqualTo(Date date, Date compareDate) {
        return date.after(compareDate) || date.equals(compareDate);
    }

    private boolean dateLessThanOrEqualTo(Date date, Date compareDate) {
        return date.before(compareDate) || date.equals(compareDate);
    }
}
