package com.capgemini.molveno.reserveringssysteem;

import com.capgemini.molveno.reserveringssysteem.io.RoomExcelDeserializer;
import com.capgemini.molveno.reserveringssysteem.model.Address;
import com.capgemini.molveno.reserveringssysteem.model.Guest;
import com.capgemini.molveno.reserveringssysteem.model.Room;
import com.capgemini.molveno.reserveringssysteem.model.Booking;
import com.capgemini.molveno.reserveringssysteem.repository.BookingRepository;
import com.capgemini.molveno.reserveringssysteem.repository.RoomRepository;
import org.hibernate.type.TimeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class BookingSystemApplication {

    private final RoomExcelDeserializer excelDeserializer;
    private final RoomRepository roomRepository;

    private final BookingRepository bookingRepository;

    @Autowired
    public BookingSystemApplication(RoomExcelDeserializer excelDeserializer, RoomRepository roomRepository, BookingRepository bookingRepository) {
        this.excelDeserializer = excelDeserializer;
        this.roomRepository = roomRepository;
        this.bookingRepository = bookingRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationStart() throws IOException {
        File excelFile = new File("Hotel kamers plus prices v0.2.xlsx");

        List<Room> roomsFromExcelSheet = this.excelDeserializer.deserialize(excelFile);

        this.roomRepository.saveAll(roomsFromExcelSheet);

        Booking testBooking = new Booking(new ArrayList<>(Arrays.asList(roomsFromExcelSheet.get(0), roomsFromExcelSheet.get(1))),
                "Extra badhandoeken en een invalide kamer",
                LocalDateTime.of(LocalDate.of(2019, 10, 15), LocalTime.of(15, 0)),
                LocalDateTime.of(LocalDate.of(2019, 10, 22), LocalTime.of(11, 30)));

        Address address = new Address("Bondstreet", 1007, null, "6007JB", "Bondtown", "Bondville");
        Guest guest = new Guest("James", "Bond", "0600700707", new Date(), "james.bond@007.com", address);
        testBooking.setGuest(guest);

        testBooking.setCreationDate(LocalDateTime.now());

        this.bookingRepository.saveAndFlush(testBooking); //TODO this is test code, replace with a POST request!
    }

    public static void main(String[] args) {
        SpringApplication.run(BookingSystemApplication.class, args);
    }

}
