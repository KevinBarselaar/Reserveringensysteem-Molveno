package com.capgemini.molveno.bookingsystem;

import com.capgemini.molveno.bookingsystem.io.RoomExcelDeserializer;
import com.capgemini.molveno.bookingsystem.model.*;
import com.capgemini.molveno.bookingsystem.repository.BookingRepository;
import com.capgemini.molveno.bookingsystem.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.io.File;
import java.io.IOException;
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

        Booking testBooking = new Booking(new ArrayList<>(Arrays.asList(roomsFromExcelSheet.get(roomsFromExcelSheet.size() -1), roomsFromExcelSheet.get(17))),
                "Extra badhandoeken en een invalide kamer",
                "01/01/2000",
                "01/08/2000");
        testBooking.setBoardType(BoardType.BED_AND_BREAKFAST);
        testBooking.setCheckedIn(true);

        Address address = new Address("Bondstreet", 11, "B", "6007 JB", "Ruhr Valley", "United Kingdom");
        MainGuest mainGuest = new MainGuest(Title.MR, "James", "Bond", new Date(), "+44-700700707", "james.bond@007.com", address);
        testBooking.setMainGuest(mainGuest);

        Guest guest1 = new Guest(Title.MR,"Freek", "Vonk", new Date());
        Guest guest2 = new Guest(Title.MR, "Peter", "R. de Vries", new Date());
        Guest guest3 = new Guest(Title.MS, "Anna", "Nas", new Date());
        List<Guest> guests = new ArrayList<>();
        guests.add(guest1);
        guests.add(guest2);
        guests.add(guest3);
        testBooking.setGuests(guests);

        testBooking.setCreationDate(LocalDateTime.now());

        this.bookingRepository.saveAndFlush(testBooking); //TODO this is test code, replace with a POST request!
    }

    public static void main(String[] args) {
        SpringApplication.run(BookingSystemApplication.class, args);
    }

}
