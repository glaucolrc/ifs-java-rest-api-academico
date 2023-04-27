package br.edu.ifs.academico.rest.dto;

import br.edu.ifs.academico.model.GeneroModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeneroDto {
    private Long codigoGenero;
    private String descricao;
}
