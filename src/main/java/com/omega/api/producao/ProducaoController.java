package com.omega.api.producao;

import com.omega.api.models.*;
import com.omega.api.producao.dtos.CreateProducaoDto;
import com.omega.api.producao.dtos.UpdateProducaoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/producoes")
public class ProducaoController {

    @Autowired
    private ProducaoService producaoService;


    @GetMapping
    public Map<String, Object> getAllProduction(
            @RequestParam(required = false, defaultValue = "") String search,
            @RequestParam(required = false, defaultValue = "0") String nroProducao,
            @RequestParam(required = false, defaultValue = "10") int take,
            @RequestParam(required = false, defaultValue = "0") int skip) {
        List<Producao> producao = producaoService.getAllProduction(search,nroProducao, take, skip);
        long total = producao.size();

        int pages = (int) Math.ceil((double) total / take);

        Map<String, Object> response = new HashMap<>();
        response.put("data", producao);
        response.put("total", total);
        response.put("pages", pages);

        return response;
    }

    @PostMapping("/novo")
    public Producao createProducao(@RequestBody CreateProducaoDto createProducaoDto) {
        System.out.println("createProducao dto" + createProducaoDto);
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

    @GetMapping("/{idProducao}")
    public Producao getProductionById(@PathVariable Long idProducao) {
        return producaoService.getProductionById(idProducao);
    }

}
