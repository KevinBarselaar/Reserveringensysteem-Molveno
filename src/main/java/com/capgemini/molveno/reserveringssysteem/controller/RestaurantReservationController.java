package com.capgemini.molveno.reserveringssysteem.controller;

import com.capgemini.molveno.reserveringssysteem.model.RestaurantReservation;
import com.capgemini.molveno.reserveringssysteem.services.RestaurantReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class RestaurantReservationController {

    private final RestaurantReservationService restaurantReservationService;

    @Autowired
    public RestaurantReservationController(RestaurantReservationService restaurantReservationService) {
        this.restaurantReservationService = restaurantReservationService;
    }

    @GetMapping("/overview")
    public List<RestaurantReservation> getAllReservations() {
        return this.restaurantReservationService.findAll();
    }

    @GetMapping("/{id}")
    public RestaurantReservation getReservationById(@PathVariable Long id) {
        return this.restaurantReservationService.findById(id);
    }

    @PostMapping("/create")
    public void createReservation(@RequestBody RestaurantReservation reservation) {
        this.restaurantReservationService.create(reservation);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        this.restaurantReservationService.deleteById(id);
    }

    @GetMapping("/nepreservering")
    public RestaurantReservation getFakeReservation() {
        RestaurantReservation reservation = new RestaurantReservation(LocalDateTime.now(), 6, "Invalide persoon en super veel kut kinderen", 200, 7);
        return reservation;
    }

}
