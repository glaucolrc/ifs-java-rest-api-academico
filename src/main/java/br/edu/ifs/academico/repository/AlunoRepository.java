package br.edu.ifs.academico.repository;

import br.edu.ifs.academico.model.AlunoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlunoRepository extends JpaRepository<AlunoModel, Long> {

    /***************************************************
        Outras formas de obtenção de dados "SELECT"
     ***************************************************/

    //Usando o operador LIKE
    List<AlunoModel> findByNomeContaining(String nome);

    //Usando a cláusula ORDER BY DESC
    List<AlunoModel> findByOrderByNomeDesc();

    //Buscando através do CPF
    Optional<AlunoModel> findByCpf(String cpf);

    //Buscando através do E-mail
    Optional<AlunoModel> findByEmail(String email);

    //Montando um SQL Nativo conforme a regra de negócio especificada... O CÉU É O LIMITE!
    @Query(value = "SELECT * FROM tb_alunos a " + "WHERE a.email = :email", nativeQuery = true)
    List<AlunoModel> findAllByEmail(@Param("email") String email);

}
