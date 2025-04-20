package com.omega.api.users;

import com.omega.api.users.dtos.GetUsersDto;
import com.omega.api.models.Usuario;
import com.omega.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsersService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<GetUsersDto> listarUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();

        return usuarios.stream().map(usuario -> new GetUsersDto(
                usuario.getId(),
                usuario.getNome(),
                usuario.getSobrenome(),
                usuario.getEmail(),
                usuario.getStatus(),
                usuario.getRoles()
        )).collect(Collectors.toList());
    }
}
