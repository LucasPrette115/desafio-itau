package com.prette.desafio_itau.business.services;


import com.prette.desafio_itau.controllers.dtos.EstatisticasResponseDTO;
import com.prette.desafio_itau.controllers.dtos.TransacaoRequestDTO;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
public class EstatisticasService {

    public final TransacaoService transacaoService;

    public EstatisticasService(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    public EstatisticasResponseDTO calcularEstatisticasDeTransacoes(Integer intervalo) {

        List<TransacaoRequestDTO> transacoes = transacaoService.buscarTransacoes(intervalo);

        DoubleSummaryStatistics estatisticasTransacoes = transacoes.stream()
                .mapToDouble(TransacaoRequestDTO::valor).summaryStatistics();

        return new EstatisticasResponseDTO(
                estatisticasTransacoes.getCount(),
                estatisticasTransacoes.getSum(),
                estatisticasTransacoes.getAverage(),
                estatisticasTransacoes.getMin(),
                estatisticasTransacoes.getMax());
    }
}
