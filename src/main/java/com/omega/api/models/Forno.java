package com.omega.api.models;

import com.omega.api.enums.SituacaoForno;
import com.omega.api.enums.StatusForno;
import jakarta.persistence.*;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Data
@Entity
@SequenceGenerator(name = "FORNOS_ID_SEQ", sequenceName = "FORNOS_ID_SEQ", allocationSize = 1, schema = "omega")
@Table(name = "fornos", schema = "omega")
public class Forno {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FORNOS_ID_SEQ")
    private String id;

    @Column(name = "nro_forno")
    private Integer nroForno;

    @Column(name = "status")
    private StatusForno status;

    @Column(name = "situacao")
    private SituacaoForno situacao;
}
