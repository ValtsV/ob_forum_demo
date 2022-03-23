package com.valts.ob_forum_demo.controllers;

import com.valts.ob_forum_demo.models.Curso;
import com.valts.ob_forum_demo.servicios.implementations.CursoServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CursosController {

    private CursoServiceImpl cursoService;

    public CursosController(CursoServiceImpl cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping("/foro/cursos")
    public ResponseEntity<List<Curso>> getCursos() {
      List<Curso> cursos = cursoService.findAll();
        return ResponseEntity.ok(cursos);
    }

    @GetMapping("/foro/cursos/{id}")
    public ResponseEntity<Curso> getCurso(@PathVariable Long id) {
        Curso curso = cursoService.findOne(id);
        return ResponseEntity.ok(curso);
    }


    @PostMapping("/foro/cursos")
    public ResponseEntity<Curso> addCurso(@RequestBody Curso curso) {
        Curso savedCurso =  cursoService.addCurso(curso);
        return ResponseEntity.ok(savedCurso);
    }

    @PutMapping("/foro/cursos/{id}")
    public ResponseEntity<Curso> updateCurso(@PathVariable Long id, @RequestBody Curso curso) {
        Curso updatedCurso = cursoService.updateCurso(id, curso);
        return ResponseEntity.ok(updatedCurso);
    }

    @DeleteMapping("/foro/cursos/{id}")
    public ResponseEntity deleteCurso(@PathVariable Long id)  {
        cursoService.deleteCurso(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/foro/cursos")
    public ResponseEntity deleteAllCursos() {
        cursoService.deleteAllCursos();
        return ResponseEntity.noContent().build();
    }



}
