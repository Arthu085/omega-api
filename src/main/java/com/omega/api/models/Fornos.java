package com.omega.api.models;

import com.omega.api.enums.SituacaoForno;
import com.omega.api.enums.StatusForno;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "fornos")
public class Fornos {
    @Id
    private String id;

    @Column(name = "nro_forno")
    private Integer nroForno;

    @Column(name = "status")
    private StatusForno status;

    @Column(name = "situacao")
    private SituacaoForno situacao;
}
