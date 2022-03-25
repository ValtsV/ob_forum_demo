package com.valts.ob_forum_demo.controllers;

import com.valts.ob_forum_demo.dto.TemaDTO;
import com.valts.ob_forum_demo.models.Curso;
import com.valts.ob_forum_demo.models.Tema;
import com.valts.ob_forum_demo.servicios.TemaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
public class TemaController {

    private TemaService temaService;

    public TemaController(TemaService temaService) {
        this.temaService = temaService;
    }

//    @GetMapping("/foro/temas")
//    public ResponseEntity<List<Tema>> getTemas() {
//        List<Tema> temas = temaService.findAll();
//        return ResponseEntity.ok(temas);
//    }

//    @GetMapping("/foro/temas/{id}")
//    public ResponseEntity<Tema> getTema(@PathVariable Long id) {
//        Tema tema = temaService.findOne(id);
//        return ResponseEntity.ok(tema);
//    }


    @PostMapping("/foro/temas")
    public ResponseEntity<Tema> addTema(@RequestBody Tema tema) {
        Tema savedTema =  temaService.addTema(tema);
        return ResponseEntity.ok(savedTema);
    }

    @PutMapping("/foro/temas/{id}")
    public ResponseEntity<Tema> updateTema(@PathVariable Long id, @RequestBody Tema tema) {
        Tema updatedTema = temaService.updateTema(id, tema);
        return ResponseEntity.ok(updatedTema);
    }

    @DeleteMapping("/foro/temas/{id}")
    public ResponseEntity deleteTema(@PathVariable Long id)  {
        temaService.deleteTema(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/foro/temas")
    public ResponseEntity deleteAllTemas() {
        temaService.deleteAllTemas();
        return ResponseEntity.noContent().build();
    }

//    --------  DTO stuff

    @GetMapping("/foro/temas/{id}")
    public ResponseEntity<TemaDTO> getTema(@PathVariable Long id) {
//        TODO: Only admins can see if Tema is pinned?
        TemaDTO tema = temaService.getTemaDto(id);
        return ResponseEntity.ok(tema);
    }


    @GetMapping("/foro/temas")
    public ResponseEntity<List<Tema>> getTemas(@RequestParam Long cursoId, @RequestParam(required = false) List<Long> moduloId) {
//        TODO: Add access modifiers
//        TODO: Sort by pinned first
        //        get list of temas from service

        List<Tema> temas = temaService.getTemas2(cursoId, moduloId);
        return ResponseEntity.ok(temas);
    }


}
