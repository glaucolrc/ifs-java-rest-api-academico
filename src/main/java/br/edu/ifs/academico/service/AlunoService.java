package br.edu.ifs.academico.service;

import br.edu.ifs.academico.rest.dto.AlunoDto;
import br.edu.ifs.academico.model.AlunoModel;
import br.edu.ifs.academico.repository.AlunoRepository;
import br.edu.ifs.academico.rest.form.AlunoForm;
import br.edu.ifs.academico.rest.form.AlunoUpdateForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlunoService {

    @Autowired
    AlunoRepository alunoRepository;

    public AlunoDto create(AlunoForm form) {
        AlunoModel alunoModel = convertToBusiness(form);
        alunoModel = alunoRepository.save(alunoModel);
        return convertToDto(alunoModel);
    }

    public List<AlunoDto> findAll(){
        List<AlunoModel> alunoModelList = alunoRepository.findAll();
        return convertListToDto(alunoModelList);
    }

    public AlunoDto findById(long matricula) {
        Optional<AlunoModel> optionalAlunoModel = alunoRepository.findById(matricula);
        if (optionalAlunoModel.isPresent()) {
            return convertToDto(optionalAlunoModel.get());
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
            return convertToDto(obj);
        }
        return null;
    }

    public void deleteById(long matricula) {
        if (alunoRepository.existsById(matricula)) {
            alunoRepository.deleteById(matricula);
        }
    }

    private AlunoModel convertToBusiness(AlunoForm alunoForm) {
        AlunoModel alunoModel = new AlunoModel();
        alunoModel.setNome(alunoForm.getNome());
        alunoModel.setEmail(alunoForm.getEmail());
        alunoModel.setCpf(alunoForm.getCpf());
        alunoModel.setDataNascimento(alunoForm.getDataNascimento());
        return alunoModel;
    }

    private AlunoDto convertToDto(AlunoModel alunoMoodel) {
        AlunoDto alunoDto = new AlunoDto();
        alunoDto.setMatricula(alunoMoodel.getMatricula());
        alunoDto.setNome(alunoMoodel.getNome());
        alunoDto.setEmail(alunoMoodel.getEmail());
        alunoDto.setCpf(alunoMoodel.getCpf());
        alunoDto.setDataNascimento(alunoMoodel.getDataNascimento());
        return alunoDto;
    }

    private static List<AlunoDto> convertListToDto(List<AlunoModel> alunos) {
        return alunos.stream().map(AlunoDto::new).collect(Collectors.toList());
    }

}
