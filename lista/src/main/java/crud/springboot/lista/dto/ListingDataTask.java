    package crud.springboot.lista.dto;

    import crud.springboot.lista.entidade.Priority;
    import crud.springboot.lista.entidade.Task;

    import java.time.LocalDate;
    import java.time.LocalDateTime;
    import java.util.Date;

    public record ListingDataTask(Long id, String titulo, String descricao, LocalDate dataVencimento, Priority prioridade){

        public ListingDataTask(Task task) {
            this(task.getId(), task.getTitulo(), task.getDescricao(), task.getDataVencimento(), task.getPrioridade());
        }
    }
