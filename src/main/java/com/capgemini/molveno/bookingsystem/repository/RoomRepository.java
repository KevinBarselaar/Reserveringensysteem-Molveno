package com.capgemini.molveno.bookingsystem.repository;

import com.capgemini.molveno.bookingsystem.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    @Query("SELECT r from room r WHERE r.available = true")
    public List<Room> findAllAvailable();
}
