package com.omega.api.repository;

import com.omega.api.models.UsuariosRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncaoUsuarioRepository extends JpaRepository<UsuariosRole, Long> {

    UsuariosRole findByIdUser(Long userId);


}
