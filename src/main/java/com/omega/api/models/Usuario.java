package com.omega.api.models;

import com.omega.api.enums.StatusUsuario;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@SequenceGenerator(name = "USUARIOS_ID_SEQ", sequenceName = "USUARIOS_ID_SEQ", allocationSize = 1, schema = "omega")
@Table(name = "usuarios", schema = "omega")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USUARIOS_ID_SEQ")
    @Column(name = "id")
    private Long id;

    @Setter
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "usuarios_roles",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_role"), schema = "omega")
    private List<Role> roles;

    @Setter
    @Column(name = "email")
    private String email;

    @Setter
    @Column(name = "senha")
    private String senha;

    @Setter
    @Column(name = "status")
    @Convert(converter = StatusUsuario.StatusUsuarioConverter.class)
    private StatusUsuario status;

    @Setter
    @Column(name = "nome")
    private String nome;

    @Setter
    @Column(name = "sobrenome")
    private String sobrenome;

}