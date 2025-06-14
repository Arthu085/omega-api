package com.omega.api.auth;

import com.omega.api.auth.dtos.CreateUserDto;
import com.omega.api.auth.dtos.LoginUserDto;
import com.omega.api.auth.dtos.RecoveryJwtTokenDto;
import com.omega.api.configuration.SecurityConfiguration;
import com.omega.api.configuration.exception.ValidationException;
import com.omega.api.enums.StatusUsuario;
import com.omega.api.models.Role;
import com.omega.api.models.Usuario;
import com.omega.api.repository.UsuarioRepository;
import com.omega.api.security.JwtTokenService;
import com.omega.api.security.userdetailimp.UserDetailImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.omega.api.auth.dtos.UsuarioResponseDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
                new UsernamePasswordAuthenticationToken(loginUserDto.email(), loginUserDto.senha());
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailImpl userDetails = (UserDetailImpl) authentication.getPrincipal();
        return new RecoveryJwtTokenDto(jwtTokenService.generateToken(userDetails));
    }

    public UsuarioResponseDto getAuthenticateUser(String email) {
        Usuario user = usuarioRepository.findByEmail(email).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
        return new UsuarioResponseDto(user.getId(),
                user.getNome(),
                user.getSobrenome(),
                user.getEmail(),
                user.getStatus(),
                user.getRoles());
    }

}