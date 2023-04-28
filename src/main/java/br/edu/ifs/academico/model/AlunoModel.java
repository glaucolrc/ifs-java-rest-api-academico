package br.edu.ifs.academico.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name="TB_ALUNOS")
public class AlunoModel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long matricula;

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @Column(name = "email", length = 80, nullable = false, unique = true)
    private String email;

    @Column(name = "cpf", length = 11, nullable = false, unique = true)
    private String cpf;

    @Column(name = "dataNascimento", nullable = false)
    private LocalDate dataNascimento;

    @ManyToOne
    @JoinColumn(name = "genero_id", referencedColumnName = "codigo")
    private GeneroModel generoModel;
}
