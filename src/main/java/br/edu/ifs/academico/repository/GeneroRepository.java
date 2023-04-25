package br.edu.ifs.academico.repository;

import br.edu.ifs.academico.model.GeneroModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeneroRepository extends JpaRepository<GeneroModel, Long> {
}
