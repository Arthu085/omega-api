package com.omega.api.repository;

import com.omega.api.models.Producao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProducaoRepository extends JpaRepository<Producao, Long> {
}