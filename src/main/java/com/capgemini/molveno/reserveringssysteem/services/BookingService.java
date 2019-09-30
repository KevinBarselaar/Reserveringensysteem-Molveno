package com.capgemini.molveno.reserveringssysteem.services;

import com.capgemini.molveno.reserveringssysteem.exception.DeadlineExpiredException;
import com.capgemini.molveno.reserveringssysteem.model.Booking;
import com.capgemini.molveno.reserveringssysteem.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public Booking findById(Long id) {
        return this.bookingRepository.findById(id).get();
    }

    public List<Booking> findAll() {
        return this.bookingRepository.findAll();
    }

    public Booking create(Booking booking) {
        return this.bookingRepository.saveAndFlush(booking);
    }

    public void deleteById(Long id) {
        this.bookingRepository.deleteById(id);
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
}
