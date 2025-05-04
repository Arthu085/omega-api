package com.omega.api.repository;

import com.omega.api.models.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface TurnoRepository extends JpaRepository<Turno, Long> {
    Turno findByDtTurno(LocalDate dtTurno);
}