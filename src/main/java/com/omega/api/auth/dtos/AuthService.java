package com.omega.api.auth.dtos;

import com.omega.api.enums.StatusUsuario;
import com.omega.api.models.Usuario;
import com.omega.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private SecurityConfiguration securityConfiguration;

    public RecoveryJwtTokenDto authenticateUser(LoginUserDto loginUserDto) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginUserDto.usuario(), loginUserDto.senha());

        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return new  RecoveryJwtTokenDto(jwtTokenService.generateToken(userDetails));
    }

    public void createUser(CreateUserDto createUserDto) {

        Optional<Usuario> usuarioEmailRepetido =  usuarioRepository.findByEmail(createUserDto.email());
        if(usuarioEmailRepetido.isPresent()){
            throw new RuntimeException("Já possui um usuário com o email informado");
        }
        Usuario newUser = Usuario.builder()
                .nome(createUserDto.nome())
                .sobrenome(createUserDto.sobrenome())
                .email(createUserDto.email())
                .senha(securityConfiguration.passwordEncoder().encode(createUserDto.senha()))
                .status(String.valueOf(StatusUsuario.ATIVO))
                .build();

        usuarioRepository.save(newUser);
    }
}
