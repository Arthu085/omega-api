package com.omega.api.auth.dtos;

import com.omega.api.enums.StatusUsuario;

import java.util.List;

public class UsuarioResponseDto {
    private Long id;
    private String nome;
    private String sobrenome;
    private String email;
    private StatusUsuario status;
    private String senha;

    public UsuarioResponseDto(Long id, String nome, String sobrenome, String email, StatusUsuario status, String senha) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.status = status;
        this.senha = senha;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setStatus(StatusUsuario status) {
        this.status = status;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public StatusUsuario getStatus() {
        return status;
    }

    public String getSenha() {
        return senha;
    }
}
