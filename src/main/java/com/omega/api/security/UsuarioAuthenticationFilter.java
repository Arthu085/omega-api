package com.omega.api.security;

import com.omega.api.configuration.SecurityConfiguration;
import com.omega.api.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@Component
public class UsuarioAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Verifica se o endpoint requer autenticação antes de processar a requisição
//        if (checkIfEndpointIsNotPublic(request)) {
//            String token = recoveryToken(request); // Recupera o token do cabeçalho Authorization da requisição
//            if (token != null) {
//                String subject = jwtTokenService.getSubjectFromToken(token); // Obtém o assunto (neste caso, o nome de usuário) do token
//                Usuario usuario = usuarioRepository.findByEmail(subject).get(); // Busca o usuário pelo email (que é o assunto do token)
//                UserDetailImpl userDetails = new UserDetailImpl(usuario); // Cria um UserDetails com o usuário encontrado
//
//                // Cria um objeto de autenticação do Spring Security
//                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken());
////                CustomAuthenticationToken authentication =
////                        new CustomAuthenticationToken(userDetails.getUsername(),
////                                entidade,
////                                token,
////                                usuario.getId(),
////                                userDetails.getAuthorities());
//                // Define o objeto de autenticação no contexto de segurança do Spring Security
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//            } else {
//                throw new RuntimeException("O token está ausente.");
//            }
//        }
        filterChain.doFilter(request, response); // Continua o processamento da requisição
    }

    // Recupera o token do cabeçalho Authorization da requisição
    private String recoveryToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }

    // Verifica se o endpoint requer autenticação antes de processar a requisição
    private boolean checkIfEndpointIsNotPublic(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return !Arrays.asList(SecurityConfiguration.ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED).contains(requestURI);
    }
}