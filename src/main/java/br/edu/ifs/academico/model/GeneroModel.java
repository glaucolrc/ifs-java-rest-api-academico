package br.edu.ifs.academico.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="tb_genero")
public class GeneroModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String descricao;
}
