package crud.springboot.lista.dto;

import crud.springboot.lista.entidade.Priority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public record RegistrationDataTask(

        Long id,
        @NotBlank(message = "{titulo.obrigatorio}")
        String titulo,
        @NotBlank(message = "{descricao.obrigatorio}")
        String descricao,
        @NotNull(message = "{dataVencimento.obrigatorio}")
        LocalDate dataVencimento,
        @NotNull(message = "{prioridade.obrigatorio}")
        Priority prioridade) {


}
