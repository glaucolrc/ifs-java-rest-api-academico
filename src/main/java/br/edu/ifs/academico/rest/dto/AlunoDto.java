package br.edu.ifs.academico.rest.dto;

import br.edu.ifs.academico.model.AlunoModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AlunoDto {
    private Long matricula;
    private String nome;
    private String email;
    private String cpf;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    public AlunoDto() {

    }

    public AlunoDto(AlunoModel aluno) {
        this.matricula = aluno.getMatricula();
        this.nome = aluno.getNome();
        this.email = aluno.getEmail();
        this.cpf = aluno.getCpf();
        this.dataNascimento = aluno.getDataNascimento();
    }

}
