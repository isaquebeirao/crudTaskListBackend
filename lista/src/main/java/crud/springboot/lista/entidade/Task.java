package crud.springboot.lista.entidade;

import crud.springboot.lista.dto.RegistrationDataTask;
import crud.springboot.lista.dto.UpdateDataTask;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Table(name = "produtos")
@Entity(name = "Produto")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Task {

    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String descricao;
    private Date dataVencimento;
    @Enumerated(EnumType.STRING)
    private Priority prioridade;
    private Boolean ativo = true;

    public Task(RegistrationDataTask dados) {
        this.titulo = dados.titulo();
        this.descricao = dados.descricao();
        this.dataVencimento = dados.dataVencimento();
        this.prioridade = dados.prioridade();
        this.ativo = true;
    }

    public void atualizarInformacoes (UpdateDataTask dados) {
        if (dados.titulo() != null) {
            this.titulo = dados.titulo();
        }
        if (dados.descricao() != null) {
            this.descricao = dados.descricao();
        }
        if (dados.dataVencimento() != null) {
            this.dataVencimento = dados.dataVencimento();
        }
        if (dados.prioridade() != null) {
            this.prioridade = dados.prioridade();
        }
    }
    public void excluir(){
        this.ativo = false;
    }

    public Long getId() {
        return id;
    }
}
