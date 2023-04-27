package br.edu.ifs.academico.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="TB_GENEROS")
public class GeneroModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @Column(nullable = false, length = 50)
    private String descricao;
}
