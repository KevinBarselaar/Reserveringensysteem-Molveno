package com.capgemini.molveno.reserveringssysteem.integration.controller;

import com.capgemini.molveno.reserveringssysteem.controller.RoomController;
import com.capgemini.molveno.reserveringssysteem.repository.RoomRepository;
import com.capgemini.molveno.reserveringssysteem.services.RoomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RoomControllerTest {

    @Autowired
    private RoomRepository roomRepository;

    private RoomController roomController;

    @BeforeEach
    public void setupTest() {
        this.roomController = new RoomController(new RoomService(this.roomRepository));

        this.roomRepository.deleteAll();


    }

    @Test
    public void getAllRooms_void_returnsListOfRooms() {

    }
}
