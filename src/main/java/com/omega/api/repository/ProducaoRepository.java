package com.omega.api.repository;

import com.omega.api.models.Forno;
import com.omega.api.models.Producao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProducaoRepository extends JpaRepository<Producao, Long> {
    Page<Forno> findByLoteFritaContainingIgnoreCase(String loteFrita, Pageable pageable);
}