package br.edu.ifs.academico.service;

import br.edu.ifs.academico.model.GeneroModel;
import br.edu.ifs.academico.repository.GeneroRepository;
import br.edu.ifs.academico.rest.dto.AlunoDto;
import br.edu.ifs.academico.model.AlunoModel;
import br.edu.ifs.academico.repository.AlunoRepository;
import br.edu.ifs.academico.rest.dto.GeneroDto;
import br.edu.ifs.academico.rest.form.AlunoForm;
import br.edu.ifs.academico.rest.form.AlunoUpdateForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    AlunoRepository alunoRepository;

    @Autowired
    GeneroRepository generoRepository;

    public AlunoDto gravar(AlunoForm alunoForm) {
        AlunoModel alunoNovo = convertToAlunoModel(alunoForm);

        Optional<AlunoModel> byEmail = alunoRepository.findByEmail(alunoNovo.getEmail());
        Optional<AlunoModel> byCpf = alunoRepository.findByCpf(alunoNovo.getCpf());

        if (byEmail.isPresent()) {
            throw new IllegalStateException("E-mail já registrado.");
        }

        if (byCpf.isPresent()) {
            throw new IllegalStateException("CPF já registrado.");
        }

        alunoNovo = alunoRepository.save(alunoNovo);
        return convertToAlunoDto(alunoNovo);
    }

    public AlunoDto atualizar(AlunoUpdateForm alunoUpdateForm, long matricula) {
        Optional<AlunoModel> alunoExistente = alunoRepository.findById(matricula);
        if (alunoExistente.isPresent()) {
            AlunoModel alunoAtualizado = alunoExistente.get();
            alunoAtualizado.setNome(alunoUpdateForm.getNome());
            alunoAtualizado.setEmail(alunoUpdateForm.getEmail());
            GeneroModel generoAtualizado = new GeneroModel();
            generoAtualizado.setCodigo(alunoUpdateForm.getCodigoGenero());
            alunoAtualizado.setGeneroModel(generoAtualizado);
            alunoRepository.save(alunoAtualizado);
            return convertToAlunoDto(alunoAtualizado);
        }
        return null;
    }

    public List<AlunoDto> obterTodos(){
        List<AlunoModel> alunoList = alunoRepository.findAll();
        return convertListToDto(alunoList);
    }

    public AlunoDto obterUm(long matricula) {
        Optional<AlunoModel> alunoExistente = alunoRepository.findById(matricula);
        if (alunoExistente.isPresent()) {
            return convertToAlunoDto(alunoExistente.get());
        }
        return null;
    }

    public void remover(long matricula) {
        if (alunoRepository.existsById(matricula)) {
            alunoRepository.deleteById(matricula);
        }
    }

    private AlunoModel convertToAlunoModel(AlunoForm alunoForm) {
        AlunoModel alunoModel = new AlunoModel();
        alunoModel.setNome(alunoForm.getNome());
        alunoModel.setEmail(alunoForm.getEmail());
        alunoModel.setCpf(alunoForm.getCpf());
        alunoModel.setDataNascimento(alunoForm.getDataNascimento());
        GeneroModel generoAtualizado = new GeneroModel();
        generoAtualizado.setCodigo(alunoForm.getCodigoGenero());
        alunoModel.setGeneroModel(generoAtualizado);
        return alunoModel;
    }

    private AlunoDto convertToAlunoDto(AlunoModel alunoModel) {
        AlunoDto alunoDto = new AlunoDto();
        alunoDto.setMatricula(alunoModel.getMatricula());
        alunoDto.setNome(alunoModel.getNome());
        alunoDto.setEmail(alunoModel.getEmail());
        alunoDto.setCpf(alunoModel.getCpf());
        alunoDto.setDataNascimento(alunoModel.getDataNascimento());
        Optional<GeneroModel> optionalGeneroModel = generoRepository.findById(alunoModel.getGeneroModel().getCodigo());
        if (optionalGeneroModel.isPresent()) {
            alunoDto.setDescricaoGenero(optionalGeneroModel.get().getDescricao());
        }
        return alunoDto;
    }

    private List<AlunoDto> convertListToDto(List<AlunoModel> list){
        List<AlunoDto> alunoDtoList = new ArrayList<>();
        for (AlunoModel alunoModel : list) {
            AlunoDto alunoDto = this.convertToAlunoDto(alunoModel);
            alunoDtoList.add(alunoDto);
        }
        return alunoDtoList;
    }
}
