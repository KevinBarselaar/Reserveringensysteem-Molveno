package com.capgemini.molveno.reserveringssysteem;

import com.capgemini.molveno.reserveringssysteem.io.RoomExcelDeserializer;
import com.capgemini.molveno.reserveringssysteem.model.Guest;
import com.capgemini.molveno.reserveringssysteem.model.Room;
import com.capgemini.molveno.reserveringssysteem.model.Booking;
import com.capgemini.molveno.reserveringssysteem.repository.BookingRepository;
import com.capgemini.molveno.reserveringssysteem.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.io.File;
import java.io.IOException;
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
        File excelFile = new File("Hotel kamers v0.1.xlsx");

        List<Room> roomsFromExcelSheet = this.excelDeserializer.deserialize(excelFile);

        this.roomRepository.saveAll(roomsFromExcelSheet);

        Booking testBooking = new Booking(Long.valueOf(1), new ArrayList<>(Arrays.asList(roomsFromExcelSheet.get(0), roomsFromExcelSheet.get(1))));

        Guest guest = new Guest("Bond", "Cool house street 007", "007007", "England", new Date(), "james@bond.com");
        testBooking.setGuest(guest);

        this.bookingRepository.saveAndFlush(testBooking); //TODO this is test code, replace with a POST request!
    }

    public static void main(String[] args) {
        SpringApplication.run(BookingSystemApplication.class, args);
    }

}
