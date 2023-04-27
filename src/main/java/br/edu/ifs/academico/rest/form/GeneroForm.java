package br.edu.ifs.academico.rest.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class GeneroForm {

    @NotEmpty
    @NotBlank(message = "O campo código do gênero não pode estar em branco.")
    private Long codigo;

    @NotEmpty
    @NotBlank(message = "O campo descrição do gênero não pode estar em branco.")
    @Size(max = 50)
    private String descricao;
}
