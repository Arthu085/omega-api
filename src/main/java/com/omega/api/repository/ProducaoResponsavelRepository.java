package com.omega.api.repository;

import com.omega.api.models.ProducaoResponsavel;
import com.omega.api.models.ProducaoResponsavelId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProducaoResponsavelRepository extends JpaRepository<ProducaoResponsavel, Long> {
    Long id(ProducaoResponsavelId id);
}