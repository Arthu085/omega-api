package com.omega.api.users.dtos;

import com.omega.api.enums.RoleUser;
import com.omega.api.enums.StatusUsuario;

public record UpdateUserDto(
        String name,
        String lastname,
        String email,
        String password,
        RoleUser roleName,
        StatusUsuario statusUser
) {}
