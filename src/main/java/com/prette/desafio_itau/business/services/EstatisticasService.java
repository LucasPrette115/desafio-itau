package com.prette.desafio_itau.business.services;


import com.prette.desafio_itau.controllers.dtos.EstatisticasResponseDTO;
import com.prette.desafio_itau.controllers.dtos.TransacaoRequestDTO;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.logging.Logger;

@Service
public class EstatisticasService {

    public final TransacaoService transacaoService;

    private static final Logger logger = Logger.getLogger(EstatisticasService.class.getName());

    public EstatisticasService(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    public EstatisticasResponseDTO calcularEstatisticasDeTransacoes(Integer intervalo) {

        logger.info("Iniciada busca de estatísticas de transações dos últimos " + intervalo + " segundos");

        List<TransacaoRequestDTO> transacoes = transacaoService.buscarTransacoes(intervalo);

        DoubleSummaryStatistics estatisticasTransacoes = transacoes.stream()
                .mapToDouble(TransacaoRequestDTO::valor).summaryStatistics();

        logger.info("Estatísticas retornadas com sucesso");

        return new EstatisticasResponseDTO(
                estatisticasTransacoes.getCount(),
                estatisticasTransacoes.getSum(),
                estatisticasTransacoes.getAverage(),
                estatisticasTransacoes.getMin(),
                estatisticasTransacoes.getMax());
    }
}
