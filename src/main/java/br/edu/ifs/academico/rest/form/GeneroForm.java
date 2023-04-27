package br.edu.ifs.academico.rest.form;

import br.edu.ifs.academico.rest.dto.GeneroDto;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class GeneroForm {

    @NotEmpty
    @NotBlank(message = "O campo Descrição do Gênero não pode estar em branco.")
    @Size(max = 50)
    private String descricao;
}
