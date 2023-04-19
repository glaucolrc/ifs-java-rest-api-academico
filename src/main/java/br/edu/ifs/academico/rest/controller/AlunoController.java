package br.edu.ifs.academico.rest.controller;

import br.edu.ifs.academico.rest.dto.AlunoDto;
import br.edu.ifs.academico.rest.form.AlunoForm;
import br.edu.ifs.academico.rest.form.AlunoUpdateForm;
import br.edu.ifs.academico.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public AlunoDto register(@RequestBody @Valid AlunoForm alunoForm) {
        return alunoService.create(alunoForm);
    }

    @GetMapping
    public List<AlunoDto> list() {
        return alunoService.findAll();
    }

    @GetMapping("/{id}")
    public AlunoDto findById(@PathVariable("id") long matricula) {
        return alunoService.findById(matricula);
    }

    @PutMapping("/{id}")
    public AlunoDto updateById(@RequestBody @Valid AlunoUpdateForm alunoUpdateForm, @PathVariable("id") long matricula) {
        return alunoService.updateById(alunoUpdateForm, matricula);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") long matricula) {
        alunoService.deleteById(matricula);
    }
}
