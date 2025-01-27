package com.prette.desafio_itau.business.services;

import com.prette.desafio_itau.controllers.dtos.TransacaoRequestDTO;
import com.prette.desafio_itau.infra.exceptions.UnprocessableEntity;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class TransacaoService {

    private final List<TransacaoRequestDTO> listaTransacoes = new ArrayList<>();
    private static final Logger logger = Logger.getLogger(TransacaoService.class.getName());

    public void adicionarTransacoes(TransacaoRequestDTO dto){

        logger.info("Iniciado o processo de gravar transações " + dto);

        if(dto.dataHora().isAfter(OffsetDateTime.now())){
            logger.severe("Data e Hora maiores que a data atual");
            throw new UnprocessableEntity("Data e Hora maiores que a data e hora atuais");
        }

        if(dto.valor() < 0){
            logger.severe("Valor não pode ser negativo");
            throw new UnprocessableEntity("Valor não pode ser negativo");
        }

        listaTransacoes.add(dto);
        logger.info("Transações adicionadas com sucesso");
    }

    public void deletarTransacoes(){
        logger.info("Iniciado o processo de deleção das transações");
        listaTransacoes.clear();
        logger.info("Transações deletadas com sucesso");
    }

    public List<TransacaoRequestDTO> buscarTransacoes(Integer intervalo){

        logger.info("Iniciada a busca de transações");
        OffsetDateTime dataHoraIntervalo = OffsetDateTime.now().minusSeconds(intervalo);
        logger.info("Transações retornadas com sucesso");
        return listaTransacoes.stream()
                .filter(
                        transacao -> transacao.dataHora()
                                .isAfter(dataHoraIntervalo)).toList();
    }
}
