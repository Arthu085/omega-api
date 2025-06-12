package com.omega.api.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "usuarios_roles", schema = "omega")
public class UsuariosRole {

    @Id
    @Column(name = "id_usuario")
    @Setter
    private Long idUser;

    @Column(name = "id_role")
    @Setter
    private Long idRole;

}
