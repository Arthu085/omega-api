package com.omega.api.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.security.Timestamp;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "turnos")
public class Turnos {

    @Id
    private Long id;

    @Column(name = "nro_dia_mes")
    private Integer nroDiaMes;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "dt_turno")
    private Timestamp dtTurno;
}
