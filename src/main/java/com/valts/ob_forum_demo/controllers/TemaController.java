package com.valts.ob_forum_demo.controllers;

import com.valts.ob_forum_demo.dto.TemaDTO;
import com.valts.ob_forum_demo.models.Tema;
import com.valts.ob_forum_demo.servicios.TemaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TemaController {

    private TemaService temaService;

    public TemaController(TemaService temaService) {
        this.temaService = temaService;
    }


    @GetMapping("/foro/temas/{id}")
    public ResponseEntity<TemaDTO> getTema(@PathVariable Long id) {
//        TODO: Only admins can see if Tema is pinned?
        TemaDTO tema = temaService.getTemaById(id);
        return ResponseEntity.ok(tema);
    }


    @GetMapping("/foro/temas")
    public ResponseEntity<List<Tema>> getTemas(@RequestParam(required = false) Long cursoId, @RequestParam(required = false) List<Long> moduloId) {
//        TODO: Add access modifiers
//        TODO: Sort by pinned first
        //        get list of all temas from service
        if (cursoId == null && moduloId == null) {
            List<Tema> temas = temaService.findAll();
            return ResponseEntity.ok(temas);
        }
//        gets list of temas where cursoID, moduloId matches
        if (cursoId != null) {
            List<Tema> temas = temaService.getTemasFilteredBy(cursoId, moduloId);
            return ResponseEntity.ok(temas);
        }
        return ResponseEntity.badRequest().build();
    }

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





}
