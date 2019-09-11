package com.capgemini.molveno.reserveringssysteem;

import com.capgemini.molveno.reserveringssysteem.io.ExcelDeserializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class ReserveringssysteemApplication {

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationStart() throws IOException {
//        File excelFile = new File("Hotel kamers v0.1.xlsx");

//        ExcelDeserializer.deserlize(excelFile);

        //TODO insert the extracted kamers to the db with the kamer repository
    }

    public static void main(String[] args) {
        SpringApplication.run(ReserveringssysteemApplication.class, args);
    }

}
