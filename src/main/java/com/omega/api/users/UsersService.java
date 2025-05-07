package com.omega.api.users;

import com.omega.api.auth.dtos.CreateUserDto;
import com.omega.api.configuration.SecurityConfiguration;
import com.omega.api.configuration.exception.ValidationException;
import com.omega.api.enums.StatusUsuario;
import com.omega.api.models.Role;
import com.omega.api.models.Usuario;
import com.omega.api.repository.UsuarioRepository;
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

    @Autowired
    private SecurityConfiguration securityConfiguration;

    public List<Usuario> getAllUsers(String search, int take, int skip) {
        var page = search != null && !search.isEmpty()
                ? usuarioRepository.findByNomeContainingIgnoreCase(search, PageRequest.of(skip / take, take))
                : usuarioRepository.findAll(PageRequest.of(skip / take, take));

        return page.getContent();
    }

    public void createUser(CreateUserDto createUserDto) {
        Optional<Usuario> usuarioEmailRepetido =  usuarioRepository.findByEmail(createUserDto.email());
        if(usuarioEmailRepetido.isPresent()){
            throw new ValidationException("Já possui um usuário com o email informado");
        }

        Usuario newUser = Usuario.builder()
                .nome(createUserDto.nome())
                .sobrenome(createUserDto.sobrenome())
                .email(createUserDto.email())
                .senha(securityConfiguration.passwordEncoder().encode(createUserDto.senha()))
                .status(StatusUsuario.ATIVO)
                .roles(List.of(Role.builder().roleName(createUserDto.role()).build()))
                .build();

        usuarioRepository.save(newUser);
    }
}
