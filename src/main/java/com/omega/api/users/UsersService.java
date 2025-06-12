package com.omega.api.users;

import com.omega.api.auth.dtos.CreateUserDto;
import com.omega.api.configuration.SecurityConfiguration;
import com.omega.api.configuration.exception.ValidationException;
import com.omega.api.enums.StatusUsuario;
import com.omega.api.models.UsuariosRole;
import com.omega.api.repository.FuncaoRepository;
import com.omega.api.repository.FuncaoUsuarioRepository;
import com.omega.api.users.dtos.UpdateUserDto;
import com.omega.api.models.Role;
import com.omega.api.models.Usuario;
import com.omega.api.repository.UsuarioRepository;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class UsersService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private FuncaoUsuarioRepository funcaoUsuarioRepository;

    @Autowired
    private FuncaoRepository funcaoRepository;

    @Autowired
    private SecurityConfiguration securityConfiguration;

    @Autowired
    private PasswordEncoder passwordEncoder;

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

        Role userRoles = funcaoRepository.findFirstByRoleName(createUserDto.role());

        Usuario newUser = Usuario.builder()
                .nome(createUserDto.nome())
                .sobrenome(createUserDto.sobrenome())
                .email(createUserDto.email())
                .senha(securityConfiguration.passwordEncoder().encode(createUserDto.senha()))
                .status(StatusUsuario.ATIVO)
                .build();

        usuarioRepository.save(newUser);

        UsuariosRole novoUsuarioRole = UsuariosRole.builder()
                .idRole(userRoles.getId())
                .idUser(newUser.getId())
                .build();

        funcaoUsuarioRepository.save(novoUsuarioRole);
    }

    public void update(Long id, UpdateUserDto dto) {
        Usuario user = usuarioRepository.findById(id)
                .orElseThrow(() -> new ValidationException("Usuario não encontrado com ID: " + id));

        if (dto.name() != null) {
            user.setNome(dto.name());
        }

        if (dto.lastname() != null) {
            user.setSobrenome(dto.lastname());
        }

        if (dto.email() != null) {
            user.setEmail(dto.email());
        }

        if (dto.password() != null) {
            String encodedPassword = passwordEncoder.encode(dto.password());
            user.setSenha(encodedPassword);
        }

        if (dto.roleName() != null) {
            Role roleId = funcaoRepository.findFirstByRoleName(dto.roleName());
            UsuariosRole roleUser = funcaoUsuarioRepository.findByIdUser(id);

            roleUser.setIdRole(roleId.getId());

            funcaoUsuarioRepository.save(roleUser);
        }

        if (dto.statusUser() != null) {
            user.setStatus(dto.statusUser());
        }

        usuarioRepository.save(user);
    }
}
