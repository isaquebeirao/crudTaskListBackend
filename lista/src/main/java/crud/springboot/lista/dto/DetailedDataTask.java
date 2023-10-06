package crud.springboot.lista.dto;

import crud.springboot.lista.entidade.Priority;
import crud.springboot.lista.entidade.Task;

import java.util.Date;

public record DetailedDataTask(Long id, String titulo, String descricao, Date dataVencimento, Priority prioridade) {

    public DetailedDataTask(Task task){
        this(task.getId(), task.getTitulo(), task.getDescricao(), task.getDataVencimento(), task.getPrioridade());
    }
}
