package com.omega.api.User.dtos;

import com.omega.api.models.Role;
import com.omega.api.models.Usuario;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserResponseDto {

    private Long id;
    private String nome;
    private String sobrenome;
    private String email;
    private List<Role> roles;;

    public UserResponseDto(Usuario user) {
        this.id = user.getId();
        this.nome = user.getNome();
        this.sobrenome = user.getSobrenome();
        this.email = user.getEmail();
        this.roles = user.getRoles();
    }
}