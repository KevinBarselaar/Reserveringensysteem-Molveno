package com.capgemini.molveno.reserveringssysteem.repository;

import com.capgemini.molveno.reserveringssysteem.model.Kamer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Kamer, Integer> {
}
