package com.omega.api.repository;

import com.omega.api.models.Forno;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FornoRepository extends JpaRepository<Forno, Long> {
    Page<Forno> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
}
