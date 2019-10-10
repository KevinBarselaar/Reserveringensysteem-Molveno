package com.capgemini.molveno.bookingsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.io.IOException;

@SpringBootApplication
public class BookingSystemApplication {
    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationStart() throws IOException {}

    public static void main(String[] args) {
        SpringApplication.run(BookingSystemApplication.class, args);
    }
}
