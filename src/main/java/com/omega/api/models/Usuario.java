package com.omega.api.models;

import com.omega.api.enums.StatusUsuario;
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
@SequenceGenerator(name = "USUARIOS_ID_SEQ", sequenceName = "USUARIOS_ID_SEQ", allocationSize = 1, schema = "omega")
@Table(name = "usuarios", schema = "omega")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USUARIOS_ID_SEQ")
    @Column(name = "id")
    private Long id;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "usuarios_roles",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_role"), schema = "omega")
    private List<Role> roles;

    @Column(name = "email")
    private String email;

    @Column(name = "senha")
    private String senha;

    @Column(name = "status")
    private StatusUsuario status;

    @Column(name = "nome")
    private String nome;

    @Column(name = "sobrenome")
    private String sobrenome;

    public Long getId() {
        return this.id;
    }


    public String getNome() {
        return this.nome;
    }

    public String getSobrenome() {
        return this.sobrenome;
    }

    public String getEmail(){
        return this.email;
    }

    public List<Role> getRoles(){
        return this.roles;
    }
}
