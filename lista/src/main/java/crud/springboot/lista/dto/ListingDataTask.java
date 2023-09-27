    package crud.springboot.lista.dto;

    import crud.springboot.lista.entidade.Priority;
    import crud.springboot.lista.entidade.Task;

    import java.util.Date;

    public record ListingDataTask(Long id, String titulo, String descricao, Date dataVencimento, Priority priority){

        public ListingDataTask(Task task) {
            this(task.getId(), task.getTitulo(), task.getDescricao(), task.getDataVencimento(), task.getPriority());
        }
    }
