package crud.springboot.lista.dto;

import crud.springboot.lista.entidade.Priority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record RegistrationDataTask(
        @NotBlank(message = "{titulo.obrigatorio}")
        String titulo,
        @NotBlank(message = "{descricao.obrigatorio}")
        String descricao,
        @NotNull(message = "{dataVencimento.obrigatorio}")
        Date dataVencimento,
        @NotNull(message = "{prioridade.obrigatorio}")
        Priority prioridade) {


}
