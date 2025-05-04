package com.omega.api.producao;

import com.omega.api.models.*;
import com.omega.api.producao.dtos.CreateProducaoDto;
import com.omega.api.producao.dtos.UpdateProducaoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/producoes")
public class ProducaoController {

    @Autowired
    private ProducaoService producaoService;

    @PostMapping
    public Producao createProducao(@RequestBody CreateProducaoDto createProducaoDto) {
        return producaoService.createProducao(createProducaoDto);
    }

    @PutMapping("/finalizar/{idProducao}")
    public Producao finalizeProducao(@PathVariable Long idProducao) {
        return producaoService.finalizaProducao(idProducao);
    }

    @PutMapping("/{idProducao}")
    public void updateProducao(@PathVariable Long idProducao, @RequestBody UpdateProducaoDto updateProducaoDto) {
        producaoService.updateProducao(idProducao, updateProducaoDto);
    }

}