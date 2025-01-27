package com.prette.desafio_itau.controllers;


import com.prette.desafio_itau.business.services.EstatisticasService;
import com.prette.desafio_itau.controllers.dtos.EstatisticasResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estatistica")
public class EstatisticasController {

    private final EstatisticasService estatisticasService;

    public EstatisticasController(EstatisticasService estatisticasService) {
        this.estatisticasService = estatisticasService;
    }

    @GetMapping
    public ResponseEntity<EstatisticasResponseDTO> buscarEstatisticas(
        @RequestParam(value = "intervaloBusca", required = false, defaultValue = "60") Integer intervalo
    )
    {
        return ResponseEntity.ok(estatisticasService.calcularEstatisticasDeTransacoes(intervalo));
    }
}
