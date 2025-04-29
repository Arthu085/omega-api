package com.omega.api.security;

import com.omega.api.models.Usuario;
import com.omega.api.repository.UsuarioRepository;
import com.omega.api.security.userdetailimp.UserDetailImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    private Usuario usuario;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(username).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return new UserDetailImpl(usuario);
    }

    public String getUsuario() {
        return usuario.toString();
    }
}
