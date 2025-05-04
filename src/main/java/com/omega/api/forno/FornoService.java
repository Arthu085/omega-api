package com.omega.api.forno;

import com.omega.api.configuration.exception.ValidationException;
import com.omega.api.enums.StatusForno;
import com.omega.api.forno.dtos.CreateFornoDto;
import com.omega.api.forno.dtos.UpdateFornoDto;
import com.omega.api.repository.FornoRepository;
import com.omega.api.models.Forno;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.Optional;

import java.util.List;

@Service
public class FornoService {

    private final FornoRepository fornoRepository;

    public FornoService(FornoRepository fornoRepository) {
        this.fornoRepository = fornoRepository;
    }

    public void create(CreateFornoDto dto) {
        Forno forno = Forno.builder()
                .nome(dto.nome())
                .nroForno(dto.nroForno())
                .status(StatusForno.ATIVO)
                .build();

        fornoRepository.save(forno);
    }

    public List<Forno> getAllFornos(String search, int take, int skip) {
        var page = search != null && !search.isEmpty()
                ? fornoRepository.findByNomeContainingIgnoreCase(search, PageRequest.of(skip / take, take))
                : fornoRepository.findAll(PageRequest.of(skip / take, take));

        List<Forno> content = page.getContent();

        return content;
    }

    public void update(Long id, UpdateFornoDto dto) {
        Forno forno = fornoRepository.findById(id)
                .orElseThrow(() -> new ValidationException("Forno não encontrado com ID: " + id));

        if (dto.getNome() != null) {
            forno.setNome(dto.getNome());
        }

        if (dto.getNroForno() != null) {
            forno.setNroForno(dto.getNroForno());
        }

        fornoRepository.save(forno);
    }

    public void delete(Long id) {
        Optional<Forno> forno = fornoRepository.findById(id);
        if (forno.isPresent()) {
            fornoRepository.deleteById(id);
        } else {
            throw new ValidationException("Forno não encontrado com o ID: " + id);
        }
    }

}