package com.omega.api.users.dtos;

import com.omega.api.enums.StatusUsuario;
import com.omega.api.models.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetUsersDto {
    private Long id;
    private String nome;
    private String sobrenome;
    private String email;
    private StatusUsuario status;
    private List<Role> roles;
}