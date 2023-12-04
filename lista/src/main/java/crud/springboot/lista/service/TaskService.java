package crud.springboot.lista.service;

import crud.springboot.lista.dto.DetailedDataTask;
import crud.springboot.lista.dto.RegistrationDataTask;
import crud.springboot.lista.dto.UpdateDataTask;
import crud.springboot.lista.dto.ListingDataTask;
import crud.springboot.lista.entidade.Task;
import crud.springboot.lista.repositorio.TaskRepository;
import org.aspectj.weaver.ast.Literal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository repository;

    public ResponseEntity cadastrarTask(RegistrationDataTask dados, UriComponentsBuilder uriBuilder) {
        Task task = new Task(dados);
        repository.save(task);
        var uri = uriBuilder.path("/product/{id}").buildAndExpand(task.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetailedDataTask(task));
    }

    public ResponseEntity<List<ListingDataTask>> listarTask () {
        List<ListingDataTask> list = repository.findAll().stream().map(ListingDataTask::new).toList();
        return ResponseEntity.ok(list);

    }

    public ResponseEntity atualizar(UpdateDataTask dados) {
        var task = repository.getReferenceById(dados.id());
        task.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DetailedDataTask(task));
    }

    public ResponseEntity excluir (Long id) {
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
