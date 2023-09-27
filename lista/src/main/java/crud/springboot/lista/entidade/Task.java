package crud.springboot.lista.entidade;

import crud.springboot.lista.dto.UpdateDataTask;
import crud.springboot.lista.dto.RegistrationDataTask;
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
    private Priority priority;
    private Boolean ativo = true;

    public Task(RegistrationDataTask dados) {
        this.titulo = dados.titulo();
        this.descricao = dados.descricao();
        this.dataVencimento = dados.dataVencimento();
        this.priority = dados.priority();
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
        if (dados.priority() != null) {
            this.priority = dados.priority();
        }
    }
    public void excluir(){
        this.ativo = false;
    }
}
