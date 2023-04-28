package br.edu.ifs.academico.rest.controller;

import br.edu.ifs.academico.rest.dto.AlunoDto;
import br.edu.ifs.academico.rest.form.AlunoForm;
import br.edu.ifs.academico.rest.form.AlunoUpdateForm;
import br.edu.ifs.academico.service.AlunoService;
import br.edu.ifs.academico.service.exceptions.ConstraintException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public List<AlunoDto> obterTodos() {
        return alunoService.obterTodos();
    }

    @GetMapping("/{id}")
    public AlunoDto obterUm(@PathVariable("id") long matricula) {
        return alunoService.obterUm(matricula);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<AlunoDto> gravar( @Valid @RequestBody AlunoForm alunoForm, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());
        return ResponseEntity.ok().body(alunoService.gravar(alunoForm));
    }

    @PutMapping("/{id}")
    public AlunoDto atualizar(@Valid @RequestBody AlunoUpdateForm alunoUpdateForm , @PathVariable("id") long matricula
            , BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());
        return alunoService.atualizar(alunoUpdateForm, matricula);
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable("id") long matricula) {
        alunoService.remover(matricula);
    }
}
