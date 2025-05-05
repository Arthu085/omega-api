package com.omega.api.models;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.omega.api.enums.StatusProducao;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@SequenceGenerator(name = "PRODUCOES_ID_SEQ", sequenceName = "PRODUCOES_ID_SEQ", allocationSize = 1, schema = "omega")
@Table(name = "producoes", schema = "omega")
public class Producao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUCOES_ID_SEQ")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_turno")
    private Turno turno;

    @ManyToOne
    @JoinColumn(name = "id_forno")
    private Forno forno;

    @Column(name = "lote_frita")
    private String loteFrita;

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
    @JsonProperty("tMovel")
    private Integer tMovel;

    @Column(name = "status")
    @Convert(converter = StatusProducao.StatusProducaoConverter.class)
    private StatusProducao status;
}
