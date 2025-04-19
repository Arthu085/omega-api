package com.omega.api.furnace;

import com.omega.api.enums.SituacaoForno;
import com.omega.api.enums.StatusForno;
import com.omega.api.furnace.dtos.CreateFurnaceDTO;
import com.omega.api.furnace.repository.FurnaceRepository;
import com.omega.api.models.Forno;
import org.springframework.stereotype.Service;

@Service
public class FurnaceService {

    private final FurnaceRepository furnaceRepository;

    public FurnaceService(FurnaceRepository furnaceRepository) {
        this.furnaceRepository = furnaceRepository;
    }

    public void create(CreateFurnaceDTO dto) {
        Forno furnace = Forno.builder()
                .nroForno(dto.nro_forno())
                .status(StatusForno.ATIVO)
                .situacao(SituacaoForno.LIGADO)
                .build();
        ;
        furnaceRepository.save(furnace);
    }
}
