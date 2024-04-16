package com.api.tarefa.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;

@Data
public class TarefaDTO {

    @NotBlank
    private String titulo;
    @NotBlank
    private String descricao;
    private Date dataCriacao;
    private Date dataVencimento;
    @NotBlank
    private String status;

}
