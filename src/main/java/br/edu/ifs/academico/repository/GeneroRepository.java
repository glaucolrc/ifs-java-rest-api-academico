package br.edu.ifs.academico.repository;

import br.edu.ifs.academico.model.AlunoModel;
import br.edu.ifs.academico.model.GeneroModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface GeneroRepository extends JpaRepository<GeneroModel, Long> {

    /***************************************************
     Outras formas de obtenção de dados "SELECT"
     ***************************************************/

    //Buscando através da Descrção do Gênero
    Optional<GeneroModel> findByDescricao(String email);

    //Método que retorna a quantidade de uso de um determinado Gênero.
    @Query(value = "SELECT COUNT(*) FROM TB_ALUNOS A INNER JOIN TB_GENEROS G ON (G.CODIGO = A.genero_id) " + "WHERE G.CODIGO = :codigoGenero", nativeQuery = true)
    Long findCountGeneroUso(@Param("codigoGenero") Long codigoGenero);
}
