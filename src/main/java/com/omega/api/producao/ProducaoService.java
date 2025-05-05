package com.omega.api.producao;

import com.omega.api.configuration.exception.ValidationException;
import com.omega.api.enums.StatusProducao;
import com.omega.api.enums.StatusProducaoResponsavel;
import com.omega.api.enums.TipoTurno;
import com.omega.api.models.*;
import com.omega.api.producao.dtos.CreateProducaoDto;
import com.omega.api.producao.dtos.UpdateProducaoDto;
import com.omega.api.repository.FornoRepository;
import com.omega.api.repository.ProducaoRepository;
import com.omega.api.repository.ProducaoResponsavelRepository;
import com.omega.api.repository.TurnoRepository;
import com.omega.api.security.userdetailimp.UserDetailImpl;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProducaoService {

    @Autowired
    private TurnoRepository turnoRepository;

    @Autowired
    private FornoRepository fornoRepository;

    @Autowired
    private ProducaoResponsavelRepository producaoResponsavelRepository;

    @Autowired
    private ProducaoRepository producaoRepository;

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public Producao createProducao(CreateProducaoDto createProducaoDto) {
        LocalDateTime now = LocalDateTime.now();
        Turno turno = handleTurnoRecovery(now);
        Optional<Forno> forno = fornoRepository.findById(createProducaoDto.idForno());
        if (forno.isEmpty()) {
            throw new ValidationException("O forno informado não foi encontrado");
        }

        Integer nroProducao = getNextNroProducao();
        Producao producao = new Producao();
        producao.setNroProducao(nroProducao);
        producao.setTurno(turno);
        producao.setForno(forno.get());
        producao.setLoteFrita(createProducaoDto.loteFrita());
        producao.setLote(createProducaoDto.lote());
        producao.setTemperatura(createProducaoDto.temperatura());
        producao.setOleo(createProducaoDto.oleo());
        producao.setOxigenio(createProducaoDto.oxigenio());
        producao.setHorarioInicio(now);
        producao.setPeso(createProducaoDto.peso());
        producao.setRpm(createProducaoDto.rpm());
        producao.setTMovel(createProducaoDto.tMovel());
        producao.setStatus(StatusProducao.EXECUTANDO);
        producao = producaoRepository.save(producao);

        handleStateProducaoResponsavel(producao, StatusProducaoResponsavel.PRODUCAO_INICIADA);
        return producao;
    }



    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public Producao finalizaProducao(Long idProducao) {
        Optional<Producao> producao = producaoRepository.findById(idProducao);
        if (producao.isEmpty()) {
            throw new ValidationException("Produção não encontrada");
        }

        if (StatusProducao.FINALIZADA.equals(producao.get().getStatus())) {
            throw new ValidationException("Produção já foi finalizada");
        }

        Producao producaoToSave = producao.get();
        producaoToSave.setStatus(StatusProducao.FINALIZADA);
        producaoToSave.setHorarioFim(LocalDateTime.now());
        producaoToSave = producaoRepository.save(producaoToSave);

        handleStateProducaoResponsavel(producaoToSave, StatusProducaoResponsavel.PRODUCAO_FINALIZADA);
        return producaoToSave;
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public Producao updateProducao(Long idProducao, UpdateProducaoDto updateProducaoDto) {
        Optional<Producao> producao = producaoRepository.findById(idProducao);
        if (producao.isEmpty()) {
            throw new ValidationException("Produção não encontrada");
        }

        Producao producaoToUpdate = producao.get();

        Optional<Forno> forno = fornoRepository.findById(updateProducaoDto.idForno());
        if (forno.isEmpty()) {
            throw new ValidationException("Forno não encontrado");
        }

        producaoToUpdate.setForno(forno.get());
        producaoToUpdate.setLoteFrita(updateProducaoDto.loteFrita());
        producaoToUpdate.setLote(updateProducaoDto.lote());
        producaoToUpdate.setTemperatura(updateProducaoDto.temperatura());
        producaoToUpdate.setOleo(updateProducaoDto.oleo());
        producaoToUpdate.setOxigenio(updateProducaoDto.oxigenio());
        producaoToUpdate.setPeso(updateProducaoDto.peso());
        producaoToUpdate.setRpm(updateProducaoDto.rpm());
        producaoToUpdate.setTMovel(updateProducaoDto.tMovel());
        return producaoRepository.save(producaoToUpdate);
    }

    private Turno handleTurnoRecovery(LocalDateTime now) {
        Turno turno = turnoRepository.findByDtTurno(LocalDate.from(now));
        if (Objects.isNull(turno)) {
            turno = createTurno(now);
        }
        return turno;
    }

    private Turno createTurno(LocalDateTime now) {
        Turno turnoToSave = new Turno();
        turnoToSave.setDtTurno(LocalDate.from(now));
        turnoToSave.setNroDiaMes(now.getDayOfMonth());
        int horario = now.getHour();
        if (horario >= 6 && horario <= 18) {
            turnoToSave.setTipo(TipoTurno.DIA);
        } else {
            turnoToSave.setTipo(TipoTurno.NOITE);
        }
        return turnoRepository.save(turnoToSave);
    }

    private Integer getNextNroProducao() {
        List<Producao> producoes = producaoRepository.findAll(Sort.by(Sort.Direction.DESC, "nroProducao"));
        return producoes.isEmpty() ? 1 : producoes.get(0).getNroProducao() + 1;
    }

    private void handleStateProducaoResponsavel(Producao producao, StatusProducaoResponsavel statusProducaoResponsavel) {
        Usuario usuarioLogado = ((UserDetailImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsuario();
        ProducaoResponsavel producaoResponsavel = new ProducaoResponsavel();
        ProducaoResponsavelId idProducaoResponsavel = new ProducaoResponsavelId(producao.getId(), usuarioLogado.getId(), statusProducaoResponsavel.getValue());
        producaoResponsavel.setId(idProducaoResponsavel);
        producaoResponsavel.setProducao(producao);
        producaoResponsavel.setResponsavel(usuarioLogado);
        producaoResponsavel.setStatus(statusProducaoResponsavel);
        producaoResponsavelRepository.save(producaoResponsavel);
    }


    public List<Producao> getAllProduction(String search, int take, int skip) {
        var page = search != null && !search.isEmpty()
                ? producaoRepository.findByLoteFritaContainingIgnoreCase(search, PageRequest.of(skip / take, take))
                : producaoRepository.findAll(PageRequest.of(skip / take, take));

        System.out.println(page.getContent());

        return (List<Producao>) page.getContent();

    }
    public Producao getProductionById(Long id) {
        return producaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produção não encontrada com ID: " + id));
    }

}