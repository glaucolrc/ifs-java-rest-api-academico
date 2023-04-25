package br.edu.ifs.academico.rest.form;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Data
public class AlunoUpdateForm {

    @NotBlank(message = "O Nome não pode estar em branco.")
    @Size(max = 100)
    private String nome;

    @NotBlank
    @Email(message = "O Endereço de e-mail é inválido.")
    @Size(max = 80)
    private String email;
}
