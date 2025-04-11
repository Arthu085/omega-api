package com.omega.api.models;

import com.omega.api.enums.StatusProducaoResponsavel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "producoes_responsaveis", schema = "omega")
public class ProducaoResponsavel {

    @EmbeddedId
    private ProducaoResponsavelId id;

    @ManyToOne
    @MapsId("idProducao")
    @JoinColumn(name = "id_producao")
    private Producao producao;

    @ManyToOne
    @MapsId("idResponsavel")
    @JoinColumn(name = "id_responsavel")
    private Usuario responsavel;

    @Column(name = "status", insertable = false, updatable = false)
    private StatusProducaoResponsavel status;
}