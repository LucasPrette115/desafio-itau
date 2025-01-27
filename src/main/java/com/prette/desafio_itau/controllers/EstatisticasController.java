package com.prette.desafio_itau.controllers;


import com.prette.desafio_itau.business.services.EstatisticasService;
import com.prette.desafio_itau.controllers.dtos.EstatisticasResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(description = "Endpoint responsável por buscar estatíticas de transações")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca efetuada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de requisição"),
            @ApiResponse(responseCode = "500", description = "Erro interno")
    })
    public ResponseEntity<EstatisticasResponseDTO> buscarEstatisticas(
        @RequestParam(value = "intervaloBusca", required = false, defaultValue = "60") Integer intervalo
    )
    {
        return ResponseEntity.ok(estatisticasService.calcularEstatisticasDeTransacoes(intervalo));
    }
}
