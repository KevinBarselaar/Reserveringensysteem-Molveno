package com.capgemini.molveno.bookingsystem.repository;

import com.capgemini.molveno.bookingsystem.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query("SELECT b from booking b WHERE b.isCheckedIn = false")
    public List<Booking> findAllCheckedOut();
}
