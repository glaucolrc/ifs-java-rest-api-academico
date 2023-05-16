package br.edu.ifs.academico.rest.controller;

import br.edu.ifs.academico.rest.dto.GeneroDto;
import br.edu.ifs.academico.rest.form.GeneroForm;
import br.edu.ifs.academico.rest.form.GeneroUpdateForm;
import br.edu.ifs.academico.service.GeneroService;
import br.edu.ifs.academico.service.exceptions.ConstraintException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/generos")
public class GeneroController {

    @Autowired
    private GeneroService generoService;

    @GetMapping
    public ResponseEntity<List<GeneroDto>> findAll() {
        List<GeneroDto> alunoDtoList = generoService.findAll();
        return ResponseEntity.ok().body(alunoDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeneroDto> find(@PathVariable("id") long codigoGenero) {
        GeneroDto generoDto = generoService.findById(codigoGenero);
        return ResponseEntity.ok().body(generoDto);
    }

    @PostMapping
    public ResponseEntity<GeneroDto> insert(@RequestBody @Valid GeneroForm generoForm, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        GeneroDto generoDto = generoService.insert(generoForm);
        return ResponseEntity.ok().body(generoDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GeneroDto> update(@RequestBody @Valid GeneroUpdateForm generoUpdateForm
            , @PathVariable("id") long codigoGenero, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        GeneroDto generoDto = generoService.update(generoUpdateForm, codigoGenero);
        return ResponseEntity.ok().body(generoDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long codigoGenero) {
        generoService.delete(codigoGenero);
        return ResponseEntity.noContent().build();
    }
}
