package com.omega.api.furnace.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateFurnanceDto {
    private String nome;
    private Integer nro_forno;

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getNro_forno() {
        return nro_forno;
    }

    public void setNro_forno(Integer nro_forno) {
        this.nro_forno = nro_forno;
    }
}
