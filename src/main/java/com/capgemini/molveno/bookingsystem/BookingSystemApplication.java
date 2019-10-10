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

    }

    public static void main(String[] args) {
        SpringApplication.run(BookingSystemApplication.class, args);
    }

}
