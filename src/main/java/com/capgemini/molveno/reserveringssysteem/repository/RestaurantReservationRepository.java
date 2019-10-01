package com.capgemini.molveno.reserveringssysteem.repository;

import com.capgemini.molveno.reserveringssysteem.model.RestaurantReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantReservationRepository extends JpaRepository<RestaurantReservation, Long> {

    @Query("SELECT b from booking b WHERE b.isCheckedIn = false")
    public List<RestaurantReservation> findAllCheckedOut();
}
