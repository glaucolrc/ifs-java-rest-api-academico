package br.edu.ifs.academico.rest.form;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


@Data
public class AlunoUpdateForm {

    @NotBlank(message = "Nome não pode estar em branco")
    private String nome;
    @Email(message = "Endereço de e-mail inválido")
    private String email;

}
