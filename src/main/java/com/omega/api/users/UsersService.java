package com.omega.api.users;

import com.omega.api.models.Usuario;
import com.omega.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> getAllUsers(String search, int take, int skip) {
        var page = search != null && !search.isEmpty()
                ? usuarioRepository.findByNomeContainingIgnoreCase(search, PageRequest.of(skip / take, take))
                : usuarioRepository.findAll(PageRequest.of(skip / take, take));

        List<Usuario> content = page.getContent();

        return content;
    }
}
