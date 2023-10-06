package crud.springboot.lista.service;

import crud.springboot.lista.dto.UpdateDataTask;
import crud.springboot.lista.dto.ListingDataTask;
import crud.springboot.lista.repositorio.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    @Autowired
    private TaskRepository repository;

//    public void salvar(RegistrationDataTask dados) {
//        var task = new Task(dados);
//        repository.save(task);
//    }

    public Page<ListingDataTask> listagemProdutos (Pageable paginacao) {
       return repository.findByAtivoTrue(paginacao).map(ListingDataTask::new);
    }

    public void atualizar(UpdateDataTask dados) {
        var produto = repository.getReferenceById(dados.id());
        produto.atualizarInformacoes(dados);
    }

    public void excluir (Long id) {
        var produto = repository.getReferenceById(id);
        produto.excluir();
    }
}
