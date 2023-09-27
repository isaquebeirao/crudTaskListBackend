package crud.springboot.lista.controller;

import crud.springboot.lista.dto.UpdateDataTask;
import crud.springboot.lista.dto.RegistrationDataTask;
import crud.springboot.lista.dto.ListingDataTask;
import crud.springboot.lista.service.TaskService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("product")
public class TaskController {

    @Autowired
    TaskService taskService;

    @PostMapping
    @Transactional
    public void cadastrarTask (@RequestBody @Valid RegistrationDataTask dados) {
        taskService.salvar(dados);
    }

    @GetMapping
    public Page<ListingDataTask> listagemTask (@PageableDefault(size = 10, sort = {"titulo"}) Pageable paginacao){
        return taskService.listagemProdutos(paginacao);
    }

    @PutMapping
    @Transactional
    public void atualizar (@RequestBody @Valid UpdateDataTask dados) {
        taskService.atualizar(dados);

    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluir (@PathVariable Long id) {
        taskService.excluir(id);
    }

}
