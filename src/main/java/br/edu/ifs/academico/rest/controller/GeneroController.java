package br.edu.ifs.academico.rest.controller;

import br.edu.ifs.academico.rest.dto.GeneroDto;
import br.edu.ifs.academico.rest.form.GeneroForm;
import br.edu.ifs.academico.rest.form.GeneroUpdateForm;
import br.edu.ifs.academico.service.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/generos")
public class GeneroController {

    @Autowired
    GeneroService generoService;

    @GetMapping
    public List<GeneroDto> obterTodos() {
        return generoService.obterTodos();
    }

    @GetMapping("/{id}")
    public GeneroDto obterUm(@PathVariable("id") long codigoGenero) {
        return generoService.obterUm(codigoGenero);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public GeneroDto gravar(@RequestBody @Valid GeneroForm generoForm) {
        return generoService.gravar(generoForm);
    }

    @PutMapping("/{id}")
    public GeneroDto atualizar(@RequestBody @Valid GeneroUpdateForm generoUpdateForm, @PathVariable("id") long codigoGenero) {
        return generoService.atualizar(generoUpdateForm, codigoGenero);
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable("id") long codigoGenero) {
        generoService.remover(codigoGenero);
    }
}
