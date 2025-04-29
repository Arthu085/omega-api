package com.omega.api.auth.dtos;

public record LoginUserDto(
        String email,
        String senha
) {}
