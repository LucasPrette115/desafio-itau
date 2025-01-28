package com.prette.desafio_itau.controllers.dtos;

public record EstatisticasResponseDTO(Long count,
                                      Double sum,
                                      Double avg,
                                      Double min,
                                      Double max) {

    public EstatisticasResponseDTO() {
        this(0L, 0.0, 0.0, 0.0, 0.0);
    }
}
