package com.omega.api.furnace.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateFurnanceDto {
    private String nome;
    private Integer nroForno;

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getNroForno() {
        return nroForno;
    }

    public void setNroForno(Integer nroForno) {
        this.nroForno = nroForno;
    }
}
