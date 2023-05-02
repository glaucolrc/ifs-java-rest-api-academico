package br.edu.ifs.academico.service;

import br.edu.ifs.academico.model.GeneroModel;
import br.edu.ifs.academico.repository.GeneroRepository;
import br.edu.ifs.academico.rest.dto.GeneroDto;
import br.edu.ifs.academico.rest.form.GeneroForm;
import br.edu.ifs.academico.rest.form.GeneroUpdateForm;
import br.edu.ifs.academico.service.exceptions.DataIntegrityException;
import br.edu.ifs.academico.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class GeneroService {

    @Autowired
    GeneroRepository generoRepository;

    public GeneroDto findById(long codigoGenero) {
        try {
            GeneroModel generoModel = generoRepository.findById(codigoGenero).get();
            return convertGeneroModelToGeneroDto(generoModel);
        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("Objeto não encontrado! Código: " + codigoGenero + ", Tipo: " + GeneroModel.class.getName());
        }
    }

    public List<GeneroDto> findAll(){
        List<GeneroModel> generoList = generoRepository.findAll();
        return convertListToDto(generoList);
    }

    public GeneroDto insert(GeneroForm generoForm) {
        try {
            GeneroModel generoNovo = convertGeneroFormToGeneroModel(generoForm);
            Optional<GeneroModel> byDescricao = generoRepository.findByDescricao(generoNovo.getDescricao());
            if (byDescricao.isPresent()) {
                throw new DataIntegrityException("A Descrição do Gênero já existe na base de dados!");
            }
            generoNovo = generoRepository.save(generoNovo);
            return convertGeneroModelToGeneroDto(generoNovo);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Gênero não foi(foram) preenchido(s).");
        }
    }

    public GeneroDto update(GeneroUpdateForm generoUpdateForm, long codigoGenero) {
        try {
            Optional<GeneroModel> generoExistente = generoRepository.findById(codigoGenero);
            if (generoExistente.isPresent()) {
                GeneroModel generoAtualizado = generoExistente.get();
                generoAtualizado.setDescricao(generoUpdateForm.getDescricao());
                generoRepository.save(generoAtualizado);
                return convertGeneroModelToGeneroDto(generoAtualizado);
            }else{
                throw new DataIntegrityException("O Código do Gênero não existe na base de dados!");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Gênero não foi(foram) preenchido(s).");
        }
    }

    public void delete(long codigoGenero) {
        try {
            if (generoRepository.existsById(codigoGenero)) {
                generoRepository.deleteById(codigoGenero);
            }
        } catch (
                DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir um Gênero que esteja associado a um Aluno!");
        }
    }

    private GeneroModel convertGeneroFormToGeneroModel(GeneroForm generoForm) {
        GeneroModel generoModel = new GeneroModel();
        generoModel.setCodigo(null);
        generoModel.setDescricao(generoForm.getDescricao());
        return generoModel;
    }

    private GeneroDto convertGeneroModelToGeneroDto(GeneroModel generoModel) {
        GeneroDto generoDto = new GeneroDto();
        generoDto.setCodigoGenero(generoModel.getCodigo());
        generoDto.setDescricao(generoModel.getDescricao());
        return generoDto;
    }

    private List<GeneroDto> convertListToDto(List<GeneroModel> list){
        List<GeneroDto> generoDtoList = new ArrayList<>();
        for (GeneroModel generoModel : list) {
            GeneroDto generoDto = this.convertGeneroModelToGeneroDto(generoModel);
            generoDtoList.add(generoDto);
        }
        return generoDtoList;
    }

    //Poderíamos usar esse método no DELETE... apenas adicionei como exemplo de validação de negócio.
    public boolean possoRemover(long codigoGenero) {
        if (generoRepository.findCountGeneroUso(codigoGenero) > 0) {
            return false;
        } else {
            return true;
        }
    }
}
