package com.capgemini.molveno.bookingsystem;

import com.capgemini.molveno.bookingsystem.io.RoomExcelDeserializer;
import com.capgemini.molveno.bookingsystem.model.*;
import com.capgemini.molveno.bookingsystem.repository.BookingRepository;
import com.capgemini.molveno.bookingsystem.repository.RoomRepository;
import com.capgemini.molveno.bookingsystem.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.io.File;
import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class BookingSystemApplication {

    private final RoomExcelDeserializer excelDeserializer;
    private final RoomRepository roomRepository;
    private final RoomService roomService;
    private final BookingRepository bookingRepository;

    @Autowired
    public BookingSystemApplication(RoomExcelDeserializer excelDeserializer, RoomRepository roomRepository, RoomService roomService, BookingRepository bookingRepository) {
        this.excelDeserializer = excelDeserializer;
        this.roomRepository = roomRepository;
        this.roomService = roomService;
        this.bookingRepository = bookingRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationStart() throws IOException {}

    public static void main(String[] args) {
        SpringApplication.run(BookingSystemApplication.class, args);
    }

}
