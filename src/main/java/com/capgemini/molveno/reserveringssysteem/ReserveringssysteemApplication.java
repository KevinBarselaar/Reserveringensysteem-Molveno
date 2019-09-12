package com.capgemini.molveno.reserveringssysteem;

import com.capgemini.molveno.reserveringssysteem.io.KamerExcelDeserializer;
import com.capgemini.molveno.reserveringssysteem.model.Kamer;
import com.capgemini.molveno.reserveringssysteem.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.io.File;
import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class ReserveringssysteemApplication {

    private final KamerExcelDeserializer excelDeserializer;
    private final RoomRepository roomRepository;

    @Autowired
    public ReserveringssysteemApplication(KamerExcelDeserializer excelDeserializer, RoomRepository roomRepository) {
        this.excelDeserializer = excelDeserializer;
        this.roomRepository = roomRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationStart() throws IOException {
        File excelFile = new File("Hotel kamers v0.1.xlsx");

        List<Kamer> kamersFromExcelsheet = this.excelDeserializer.deserialize(excelFile);

        this.roomRepository.saveAll(kamersFromExcelsheet);
    }

    public static void main(String[] args) {
        SpringApplication.run(ReserveringssysteemApplication.class, args);
    }

}
