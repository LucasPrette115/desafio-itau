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

        logger.info("Iniciado o processo de gravar transações");

        if(dto.dataHora().isAfter(OffsetDateTime.now())){
            logger.severe("Data e Hora maiores que a data atual");
            throw new UnprocessableEntity("Data e Hora maiores que a data e hora atuais");
        }

        if(dto.valor() < 0){
            logger.severe("Valor não pode ser negativo");
            throw new UnprocessableEntity("Valor não pode ser negativo");
        }

        listaTransacoes.add(dto);
    }

    public void deletarTransacoes(){
        listaTransacoes.clear();
    }

    public List<TransacaoRequestDTO> buscarTransacoes(Integer intervalo){

        OffsetDateTime dataHoraIntervalo = OffsetDateTime.now().minusSeconds(intervalo);

        return listaTransacoes.stream()
                .filter(
                        transacao -> transacao.dataHora()
                                .isAfter(dataHoraIntervalo)).toList();
    }
}
