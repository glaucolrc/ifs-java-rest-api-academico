package br.edu.ifs.academico.rest.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class GeneroUpdateForm {

    @NotBlank(message = "O campo gênero não pode estar em branco.")
    @Size(max = 50)
    private String descricao;
}
