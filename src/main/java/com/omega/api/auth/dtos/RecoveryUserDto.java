package com.omega.api.auth.dtos;

import com.omega.api.enums.RoleUser;

import java.util.List;

public record RecoveryUserDto(
        Long id,
        String usuario,
        List<RoleUser> roles
) {}
