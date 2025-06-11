package com.omega.api.users;

import com.omega.api.auth.dtos.CreateUserDto;
import com.omega.api.configuration.SecurityConfiguration;
import com.omega.api.configuration.exception.ValidationException;
import com.omega.api.enums.RoleUser;
import com.omega.api.enums.StatusUsuario;
import com.omega.api.users.dtos.UpdateUserDto;
import com.omega.api.models.Role;
import com.omega.api.models.Usuario;
import com.omega.api.repository.UsuarioRepository;

import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class UsersService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    // private RoleRepository roleRepository;

    @Autowired
    private SecurityConfiguration securityConfiguration;

    public List<Usuario> getAllUsers(String search, int take, int skip) {
        var page = search != null && !search.isEmpty()
                ? usuarioRepository.findByNomeContainingIgnoreCase(search, PageRequest.of(skip / take, take))
                : usuarioRepository.findAll(PageRequest.of(skip / take, take));

        return page.getContent();
    }

    public void createUser(CreateUserDto createUserDto) {
        Optional<Usuario> usuarioEmailRepetido = usuarioRepository.findByEmail(createUserDto.email());
        if (usuarioEmailRepetido.isPresent()) {
            throw new ValidationException("Já possui um usuário com o email informado");
        }

        List<Role> userRoles = usuarioRepository.findByRoleNameIn(createUserDto.role());

        // #TODO: chamar o repository de roles

        log.info("Creating user: " + userRoles);

        Usuario newUser = Usuario.builder()
                .nome(createUserDto.nome())
                .sobrenome(createUserDto.sobrenome())
                .email(createUserDto.email())
                .senha(securityConfiguration.passwordEncoder().encode(createUserDto.senha()))
                .status(StatusUsuario.ATIVO)
                // .roles(List.of(Role.builder().roleName(createUserDto.role()).build()))

                .build();

        usuarioRepository.save(newUser);
    }

    public void update(Long id, UpdateUserDto dto) {
        Usuario user = usuarioRepository.findById(id)
                .orElseThrow(() -> new ValidationException("Usuario não encontrado com ID: " + id));

        if (dto.getName() != null) {
            user.setNome(dto.getName());
        }

        if (dto.getLastname() != null) {
            user.setSobrenome(dto.getLastname());
        }

        if (dto.getEmail() != null) {
            user.setEmail(dto.getEmail());
        }

        if (dto.getPassword() != null) {
            user.setSenha(dto.getPassword());
            securityConfiguration.passwordEncoder().encode(dto.getPassword());
        }

        if (dto.getRoleUser() != null) {
            user.setRoles(List.of(Role.builder().roleName(dto.getRoleUser()).build()));

        }

        if (dto.getStatusUser() != null) {
            user.setStatus(dto.getStatusUser());
        }

        log.info(user);

        usuarioRepository.save(user);
    }
}
