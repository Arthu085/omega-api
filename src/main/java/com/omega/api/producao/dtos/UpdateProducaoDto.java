package com.omega.api.producao.dtos;

import java.math.BigDecimal;

public record UpdateProducaoDto(
        Long idForno,
        String loteFrita,
        Integer lote,
        BigDecimal temperatura,
        BigDecimal oleo,
        BigDecimal oxigenio,
        BigDecimal peso,
        Integer rpm,
        Integer tMovel
) {}