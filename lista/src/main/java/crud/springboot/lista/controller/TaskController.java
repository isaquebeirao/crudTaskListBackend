package crud.springboot.lista.controller;

import crud.springboot.lista.dto.DetailedDataTask;
import crud.springboot.lista.dto.UpdateDataTask;
import crud.springboot.lista.dto.RegistrationDataTask;
import crud.springboot.lista.dto.ListingDataTask;
import crud.springboot.lista.entidade.Task;
import crud.springboot.lista.repositorio.TaskRepository;
import crud.springboot.lista.service.TaskService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

//ARRUMAR O SERVICE, ADICIONAR SPRING SECURITY

@RestController
@RequestMapping("product")
public class TaskController {

    @Autowired
    TaskService taskService;
    @Autowired
    TaskRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarTask (@RequestBody @Valid RegistrationDataTask dados, UriComponentsBuilder uriBuilder) {
        Task task = new Task(dados);
        repository.save(task);
        var uri = uriBuilder.path("/product/{id}").buildAndExpand(task.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetailedDataTask(task));

    }

    @GetMapping
    public ResponseEntity<Page<ListingDataTask>> listagemTask (@PageableDefault(size = 10, sort = {"titulo"}) Pageable paginacao){
        var page = taskService.listagemProdutos(paginacao);

        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar (@RequestBody @Valid UpdateDataTask dados) {
        var task = repository.getReferenceById(dados.id());
        task.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DetailedDataTask(task));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> excluir (@PathVariable Long id) {
        taskService.excluir(id);

        return ResponseEntity.noContent().build();
    }

}
