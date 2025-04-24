package com.omega.api.furnace;

import com.omega.api.enums.SituacaoForno;
import com.omega.api.enums.StatusForno;
import com.omega.api.furnace.dtos.CreateFurnaceDTO;
import com.omega.api.furnace.dtos.UpdateFurnanceDto;
import com.omega.api.repository.FurnaceRepository;
import com.omega.api.models.Forno;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.Optional;

import java.util.List;

@Service
public class FurnaceService {

    private final FurnaceRepository furnaceRepository;

    public FurnaceService(FurnaceRepository furnaceRepository) {
        this.furnaceRepository = furnaceRepository;
    }

    public void create(CreateFurnaceDTO dto) {
        Forno furnace = Forno.builder()
                .nome(dto.nome())
                .nro_forno(dto.nro_forno())
                .status(StatusForno.ATIVO)
                .situacao(SituacaoForno.LIGADO)
                .build();

        furnaceRepository.save(furnace);
    }

    public List<Forno> getAllFurnace(String search, int take, int skip) {
        var page = search != null && !search.isEmpty()
                ? furnaceRepository.findByNomeContainingIgnoreCase(search, PageRequest.of(skip / take, take))
                : furnaceRepository.findAll(PageRequest.of(skip / take, take));

        List<Forno> content = page.getContent();

        return content;
    }

    public void update(Long id, UpdateFurnanceDto dto) {
        Forno forno = furnaceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Forno não encontrado com ID: " + id));

        if (dto.getNome() != null) {
            forno.setNome(dto.getNome());
        }

        if (dto.getNro_forno() != null) {
            forno.setNro_forno(dto.getNro_forno());
        }

        furnaceRepository.save(forno);
    }

    public void delete(Long id) {
        Optional<Forno> forno = furnaceRepository.findById(id);
        if (forno.isPresent()) {
            furnaceRepository.deleteById(id);
        } else {
            throw new RuntimeException("Forno não encontrado com o ID: " + id);
        }
    }

}