package crud.springboot.lista.dto;

import crud.springboot.lista.entidade.Priority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record RegistrationDataTask(
        @NotBlank
        String titulo,
        @NotBlank
        String descricao,
        @NotNull
        Date dataVencimento,
        @NotNull
        Priority priority) {
}
