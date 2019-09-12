package com.capgemini.molveno.reserveringssysteem.repository;

import com.capgemini.molveno.reserveringssysteem.model.Reservering;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Reservering, Long> {
}
