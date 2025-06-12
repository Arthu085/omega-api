package com.omega.api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.omega.api.enums.RoleUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@SequenceGenerator(name = "ROLES_ID_SEQ", sequenceName = "ROLES_ID_SEQ", allocationSize = 1, schema = "omega")
@Table(name = "roles", schema = "omega")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ROLES_ID_SEQ")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_name", nullable = false)
    private RoleUser roleName;

    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private List<Usuario> usuarios;
}