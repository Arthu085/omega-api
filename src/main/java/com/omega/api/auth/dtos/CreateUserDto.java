package com.omega.api.auth.dtos;

import com.omega.api.enums.RoleUser;

public record CreateUserDto(
        Long id,
        String nome,
        String sobrenome,
        String email,
        String senha,
        RoleUser role
) {}
