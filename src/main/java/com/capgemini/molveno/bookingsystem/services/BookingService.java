package com.capgemini.molveno.bookingsystem.services;

import com.capgemini.molveno.bookingsystem.exception.BookingNotFoundException;
import com.capgemini.molveno.bookingsystem.exception.DeadlineExpiredException;
import com.capgemini.molveno.bookingsystem.model.Booking;
import com.capgemini.molveno.bookingsystem.model.Room;
import com.capgemini.molveno.bookingsystem.repository.BookingRepository;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public Booking findById(Long id) {
        return this.bookingRepository.findById(id).orElseThrow(BookingNotFoundException::new);
    }

    public List<Booking> findAll() {
        return this.bookingRepository.findAll();
    }

    public List<Booking> findAllCheckedOut() {
        return this.bookingRepository.findAllCheckedOut();
    }

    public Booking create(Booking booking) { //TODO set every room available to false
        booking.setCreationDate(LocalDateTime.now());
        System.out.println(booking.getStartBooking());

        return this.bookingRepository.saveAndFlush(booking);
    }

    public void deleteById(Long id) {
        this.bookingRepository.deleteById(id);
    }

    public void checkInOutBooking(Long id) {
        Booking booking = this.findById(id);
        booking.setCheckedIn(!booking.isCheckedIn());

        for (Room room : booking.getRooms()) {
            room.setAvailable(!room.isAvailable());
        }

        this.bookingRepository.save(booking);
    }

    public void removeRoomFromBooking(Long bookingId, Long roomId) {
        Booking booking = this.findById(bookingId);

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime deadline = booking.getCreationDate().plusHours(1);

        if (now.isBefore(deadline)) {
            int roomIndexToRemove = -1;

            for (int index = 0; index < booking.getRooms().size(); index++) {
                if (booking.getRooms().get(index).getId().equals(roomId)) {
                    roomIndexToRemove = index;
                    break;
                }
            }

            if (roomIndexToRemove >= 0) {
                booking.getRooms().remove(roomIndexToRemove);

                if (booking.getRooms().isEmpty()) {
                    this.bookingRepository.delete(booking);
                } else {
                    this.bookingRepository.save(booking);
                }
            }
        } else {
            throw new DeadlineExpiredException(booking);
        }
    }

    public Booking update(Booking booking) throws BookingNotFoundException {
        this.findById(booking.getId()); // Throws Exception when Id can't be found
        return this.bookingRepository.saveAndFlush(booking);
    }
}
