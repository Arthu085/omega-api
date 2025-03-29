package com.omega.api.models;


import com.omega.api.enums.StatusProducao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "producoes")
public class Producoes {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_turno")
    private Turnos turnos;

    @ManyToOne
    @JoinColumn(name = "id_forno")
    private Fornos fornos;

    @ManyToOne
    @JoinColumn(name = "id_operador")
    private Usuario usuario;

    @Column(name = "lote_frita")
    private Integer loteFrita;

    @Column(name = "nro_producao")
    private Integer nroProducao;

    @Column(name = "lote")
    private Integer lote;

    @Column(precision = 10, scale = 2, name = "temperatura")
    private BigDecimal temperatura;

    @Column(precision = 10, scale = 2,name = "oleo")
    private BigDecimal oleo;

    @Column(precision = 10, scale = 2, name = "oxigenio")
    private BigDecimal oxigenio;

    @Column(name = "horario_inicio")
    private LocalDateTime horarioInicio;

    @Column(name = "horario_fim")
    private LocalDateTime horarioFim;

    @Column(precision = 10, scale = 2, name = "peso")
    private BigDecimal peso;

    @Column(name = "rpm")
    private Integer rpm;

    @Column(name = "t_movel")
    private Integer tMovel;

    @Column(name = "status")
    private StatusProducao status;
}
