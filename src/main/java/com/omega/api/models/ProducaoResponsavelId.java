package com.omega.api.models;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProducaoResponsavelId implements Serializable {
    private Long idProducao;
    private Long idResponsavel;
    private String status;

    public ProducaoResponsavelId() {}

    // All-args constructor
    public ProducaoResponsavelId(Long idProducao, Long idResponsavel, String status) {
        this.idProducao = idProducao;
        this.idResponsavel = idResponsavel;
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProducaoResponsavelId)) return false;
        ProducaoResponsavelId that = (ProducaoResponsavelId) o;
        return Objects.equals(idProducao, that.idProducao) &&
                Objects.equals(idResponsavel, that.idResponsavel) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProducao, idResponsavel, status);
    }
}