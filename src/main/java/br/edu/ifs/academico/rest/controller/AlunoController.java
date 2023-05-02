package br.edu.ifs.academico.rest.controller;

import br.edu.ifs.academico.rest.dto.AlunoDto;
import br.edu.ifs.academico.rest.form.AlunoForm;
import br.edu.ifs.academico.rest.form.AlunoUpdateForm;
import br.edu.ifs.academico.service.AlunoService;
import br.edu.ifs.academico.service.exceptions.ConstraintException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @GetMapping
    public ResponseEntity<List<AlunoDto>> findAll() {
        List<AlunoDto> alunoDtoList = alunoService.findAll();
        return ResponseEntity.ok().body(alunoDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoDto> find(@PathVariable("id") long matricula) {
        AlunoDto alunoDto = alunoService.findById(matricula);
        return ResponseEntity.ok().body(alunoDto);
    }

    @PostMapping
    public ResponseEntity<AlunoDto> insert(@Valid @RequestBody AlunoForm alunoForm, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        AlunoDto alunoDto = alunoService.insert(alunoForm);
        return ResponseEntity.ok().body(alunoDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoDto> update(@Valid @RequestBody AlunoUpdateForm alunoUpdateForm
            , @PathVariable("id") long matricula, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        AlunoDto alunoDto = alunoService.update(alunoUpdateForm, matricula);
        return ResponseEntity.ok().body(alunoDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long matricula) {
        alunoService.delete(matricula);
        return ResponseEntity.noContent().build();
    }
}
