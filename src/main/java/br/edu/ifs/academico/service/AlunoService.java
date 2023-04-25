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
import java.util.stream.Collectors;

@Service
public class AlunoService {

    @Autowired
    AlunoRepository alunoRepository;

    @Autowired
    GeneroRepository generoRepository;

    public AlunoDto create(AlunoForm alunoForm) {
        AlunoModel novoAluno = convertToAlunoModel(alunoForm);

        Optional<AlunoModel> byEmail = alunoRepository.findByEmail(novoAluno.getEmail());
        Optional<AlunoModel> byCpf = alunoRepository.findByCpf(novoAluno.getCpf());

        if (byEmail.isPresent()) {
            throw new IllegalStateException("E-mail já registrado.");
        }

        if (byCpf.isPresent()) {
            throw new IllegalStateException("CPF já registrado.");
        }

        if (novoAluno.getGeneroModel() != null) {
            Long id = novoAluno.getGeneroModel().getId();
            GeneroModel generoModel;
            if (id != null) {
                generoModel = this.generoRepository.getById(id);
            } else{
                generoModel = this.generoRepository.save(novoAluno.getGeneroModel());
            }
            novoAluno.setGeneroModel(generoModel);
        }

        novoAluno = alunoRepository.save(novoAluno);
        return convertToAlunoDto(novoAluno);
    }

    public List<AlunoDto> findAll(){
        List<AlunoModel> alunoModelList = alunoRepository.findAll();
        return convertListToDto(alunoModelList);
    }

    public AlunoDto findById(long matricula) {
        Optional<AlunoModel> optionalAlunoModel = alunoRepository.findById(matricula);
        if (optionalAlunoModel.isPresent()) {
            return convertToAlunoDto(optionalAlunoModel.get());
        }
        return null;
    }

    public AlunoDto updateById(AlunoUpdateForm form, long matricula) {
        Optional<AlunoModel> optionalAlunoModel = alunoRepository.findById(matricula);
        if (optionalAlunoModel.isPresent()) {
            AlunoModel obj = optionalAlunoModel.get();
            obj.setNome(form.getNome());
            obj.setEmail(form.getEmail());
            alunoRepository.save(obj);
            return convertToAlunoDto(obj);
        }
        return null;
    }

    public void deleteById(long matricula) {
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

        if (alunoForm.getGeneroForm() != null) {
            GeneroModel generoModel = new GeneroModel();
            if (alunoForm.getGeneroForm().getId() != null){
                generoModel.setId(alunoForm.getGeneroForm().getId());
            } else {
                generoModel.setDescricao(alunoForm.getGeneroForm().getDescricao());
            }
            alunoModel.setGeneroModel(generoModel);
        }

        return alunoModel;
    }

    private AlunoDto convertToAlunoDto(AlunoModel alunoModel) {
        AlunoDto alunoDto = new AlunoDto();

        alunoDto.setMatricula(alunoModel.getMatricula());
        alunoDto.setNome(alunoModel.getNome());
        alunoDto.setEmail(alunoModel.getEmail());
        alunoDto.setCpf(alunoModel.getCpf());
        alunoDto.setDataNascimento(alunoModel.getDataNascimento());

        if (alunoModel.getGeneroModel() != null){
            GeneroDto generoDto = new GeneroDto();
            generoDto.setId(alunoModel.getGeneroModel().getId());
            generoDto.setDescricao(alunoModel.getGeneroModel().getDescricao());
            alunoDto.setGeneroDto(generoDto);
        }

        return alunoDto;
    }

 //   private static List<AlunoDto> convertListToDto(List<AlunoModel> alunos) {
 //       return alunos.stream().map(AlunoDto::new).collect(Collectors.toList());
 //   }

    private List<AlunoDto> convertListToDto(List<AlunoModel> list){
        List<AlunoDto> listDto = new ArrayList<>();
        for (AlunoModel alunoModel : list) {
            AlunoDto alunoDto = this.convertToAlunoDto(alunoModel);
            listDto.add(alunoDto);
        }
        return listDto;
    }



}
