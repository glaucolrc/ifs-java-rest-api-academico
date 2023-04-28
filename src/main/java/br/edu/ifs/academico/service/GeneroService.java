package br.edu.ifs.academico.service;

import br.edu.ifs.academico.model.AlunoModel;
import br.edu.ifs.academico.model.GeneroModel;
import br.edu.ifs.academico.repository.GeneroRepository;
import br.edu.ifs.academico.rest.dto.GeneroDto;
import br.edu.ifs.academico.rest.form.GeneroForm;
import br.edu.ifs.academico.rest.form.GeneroUpdateForm;
import br.edu.ifs.academico.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GeneroService {

    @Autowired
    GeneroRepository generoRepository;

    public GeneroDto obterUm(long codigoGenero) {

        Optional<GeneroModel> generoExistente = Optional.ofNullable(generoRepository.findById(codigoGenero)
                .orElseThrow(() -> new ObjectNotFoundException("Genero de matrícula " + codigoGenero + " não encontrado!")));
        return convertToGeneroDto(generoExistente.get());
    }

    public List<GeneroDto> obterTodos(){
        List<GeneroModel> generoList = generoRepository.findAll();
        return convertListToDto(generoList);
    }

    public GeneroDto gravar(GeneroForm generoForm) {
        GeneroModel generoNovo = convertToGeneroModel(generoForm);

        Optional<GeneroModel> byDescricao = generoRepository.findByDescricao(generoNovo.getDescricao());

        if (byDescricao.isPresent()) {
            throw new IllegalStateException("A Descrição do Gênero já existe na base de dados.");
        }

        generoNovo = generoRepository.save(generoNovo);
        return convertToGeneroDto(generoNovo);
    }

    public GeneroDto atualizar(GeneroUpdateForm generoUpdateForm, long codigoGenero) {
        Optional<GeneroModel> generoExistente = generoRepository.findById(codigoGenero);
        if (generoExistente.isPresent()) {
            GeneroModel generoAtualizado = generoExistente.get();
            generoAtualizado.setDescricao(generoUpdateForm.getDescricao());
            generoRepository.save(generoAtualizado);
            return convertToGeneroDto(generoAtualizado);
        }
        return null;
    }

    public void remover(long codigoGenero) {
        if (generoRepository.existsById(codigoGenero)){
            if (possoRemover(codigoGenero)) {
                generoRepository.deleteById(codigoGenero);
            } else {
                throw new IllegalStateException("O Gênero não pode ser removido, pois estar sendo usado por aluno(s).");
            }
        }
    }

    private GeneroModel convertToGeneroModel(GeneroForm generoForm) {
        GeneroModel generoModel = new GeneroModel();
        generoModel.setCodigo(null);
        generoModel.setDescricao(generoForm.getDescricao());
        return generoModel;
    }

    private GeneroDto convertToGeneroDto(GeneroModel generoModel) {
        GeneroDto generoDto = new GeneroDto();
        generoDto.setCodigoGenero(generoModel.getCodigo());
        generoDto.setDescricao(generoModel.getDescricao());
        return generoDto;
    }

    private List<GeneroDto> convertListToDto(List<GeneroModel> list){
        List<GeneroDto> generoDtoList = new ArrayList<>();
        for (GeneroModel generoModel : list) {
            GeneroDto generoDto = this.convertToGeneroDto(generoModel);
            generoDtoList.add(generoDto);
        }
        return generoDtoList;
    }

    public boolean possoRemover(long codigoGenero) {
        if (generoRepository.findCountGeneroUso(codigoGenero) > 0) {
            return false;
        } else {
            return true;
        }
    }
}
