package crud.springboot.lista.controller;

import crud.springboot.lista.dto.UpdateDataTask;
import crud.springboot.lista.dto.RegistrationDataTask;
import crud.springboot.lista.dto.ListingDataTask;
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

import java.util.List;

@RestController
@RequestMapping("/todo")
public class TaskController {

    @Autowired
    TaskService taskService;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    @Transactional
    public ResponseEntity cadastrarTask (@RequestBody @Valid RegistrationDataTask dados, UriComponentsBuilder uriBuilder) {
        return taskService.cadastrarTask(dados, uriBuilder);
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public ResponseEntity<List<ListingDataTask>> listagemTask (){
        return taskService.listarTask();
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping
    @Transactional
    public ResponseEntity atualizar (@RequestBody @Valid UpdateDataTask dados) {
        return taskService.atualizar(dados);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> excluir (@PathVariable Long id) {
        return taskService.excluir(id);
    }

}
