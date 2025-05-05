package com.omega.api.models;

import com.omega.api.enums.TipoTurno;
import jakarta.persistence.*;
import lombok.*;

import java.security.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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
    @Convert(converter = TipoTurno.TipoTurnoConverter.class)
    private TipoTurno tipo;

    @Column(name = "dt_turno")
    private LocalDate dtTurno;
}
