package com.omega.api.repository;

import com.omega.api.models.Forno;
import com.omega.api.models.Producao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProducaoRepository extends JpaRepository<Producao, Long> {
    @Query("SELECT p FROM Producao p " +
            "WHERE " +
            "(:search IS NULL OR LOWER(p.loteFrita) LIKE LOWER(CONCAT('%', :search, '%'))) " +
            "OR (:nroProducao IS NULL OR p.nroProducao = :nroProducao) " +
            "ORDER BY p.status ASC, p.nroProducao DESC")
    Page<Producao> searchProducoes(@Param("search") String search,
                                   @Param("nroProducao") String nroProducao,
                                   Pageable pageable);
}