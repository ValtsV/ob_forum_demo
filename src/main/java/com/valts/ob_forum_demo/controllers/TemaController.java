package com.valts.ob_forum_demo.controllers;

import com.valts.ob_forum_demo.dto.TemaDTO;
import com.valts.ob_forum_demo.models.Tema;
import com.valts.ob_forum_demo.servicios.TemaService;
import com.valts.ob_forum_demo.servicios.implementations.TemaServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class TemaController {

    private TemaServiceImpl temaService;

    public TemaController(TemaServiceImpl temaService) {
        this.temaService = temaService;
    }


    @GetMapping("/foro/temas/{id}")
    public ResponseEntity<TemaDTO> getTema(@PathVariable Long id) {
//        TODO: Only admins can see if Tema is pinned?
        TemaDTO tema = temaService.getTemaById(id);
        return ResponseEntity.ok(tema);
    }

    @GetMapping("/foro/temas")
    public ResponseEntity<List<Tema>> getTemas(@RequestParam(required = false) Long cursoId, @RequestParam(required = false) List<Long> moduloId, Authentication authentication) {


        if (cursoId == null && moduloId == null) {
            List<Tema> temas = temaService.findAll(authentication.getName());
            return ResponseEntity.ok(temas);
        }

//        gets list of temas where cursoID, moduloId matches
        if (cursoId != null) {
            List<Tema> temas = temaService.getTemasFilteredBy(cursoId, moduloId);
            return ResponseEntity.ok(temas);
        }
        return ResponseEntity.badRequest().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/foro/temas")
    public ResponseEntity<Tema> addTema(@RequestBody Tema tema) {
        Tema savedTema =  temaService.addTema(tema);
        return ResponseEntity.ok(savedTema);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/foro/temas/{id}")
    public ResponseEntity<Tema> updateTema(@PathVariable Long id, @RequestBody Tema tema) {
        Tema updatedTema = temaService.updateTema(id, tema);
        return ResponseEntity.ok(updatedTema);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/foro/temas/{id}")
    public ResponseEntity deleteTema(@PathVariable Long id)  {
        temaService.deleteTema(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/foro/temas")
    public ResponseEntity deleteAllTemas() {
        temaService.deleteAllTemas();
        return ResponseEntity.noContent().build();
    }





}
