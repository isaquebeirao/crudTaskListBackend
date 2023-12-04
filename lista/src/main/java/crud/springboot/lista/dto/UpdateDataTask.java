package crud.springboot.lista.dto;

import crud.springboot.lista.entidade.Priority;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public record UpdateDataTask(
        @NotNull
        Long id,
        String titulo,
        String descricao,
        LocalDate dataVencimento,
        Priority prioridade) {
}
