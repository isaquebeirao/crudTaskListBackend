package crud.springboot.lista.entidade;

import com.fasterxml.jackson.annotation.JsonFormat;
import crud.springboot.lista.dto.RegistrationDataTask;
import crud.springboot.lista.dto.UpdateDataTask;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataVencimento;
    @Enumerated(EnumType.STRING)
    private Priority prioridade;

    public Task(RegistrationDataTask dados) {
        this.id = dados.id();
        this.titulo = dados.titulo();
        this.descricao = dados.descricao();
        this.dataVencimento = dados.dataVencimento();
        this.prioridade = dados.prioridade();
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

    public Long getId() {
        return id;
    }
}
