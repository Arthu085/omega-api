package com.omega.api.models;

import com.omega.api.enums.TipoTurno;
import jakarta.persistence.*;
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
@SequenceGenerator(name = "TURNOS_ID_SEQ", sequenceName = "TURNOS_ID_SEQ", allocationSize = 1, schema = "omega")
@Table(name = "turnos", schema = "omega")
public class Turno {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TURNOS_ID_SEQ")
    private Long id;

    @Column(name = "nro_dia_mes")
    private Integer nroDiaMes;

    @Column(name = "tipo")
    private TipoTurno tipo;

    @Column(name = "dt_turno")
    private Timestamp dtTurno;
}
