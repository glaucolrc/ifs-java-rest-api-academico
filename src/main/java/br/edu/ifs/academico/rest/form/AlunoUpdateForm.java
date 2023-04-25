package br.edu.ifs.academico.rest.form;

import lombok.Data;

import javax.validation.constraints.*;


@Data
public class AlunoUpdateForm {

    @NotEmpty
    @NotBlank(message = "O Nome não pode estar em branco.")
    @Size(max = 100)
    private String nome;

    @NotEmpty
    @NotBlank
    @Email(message = "O Endereço de e-mail é inválido.")
    @Size(max = 80)
    private String email;

    @NotNull
    private GeneroUpdateForm generoUpdateForm;
}
