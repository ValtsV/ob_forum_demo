package com.valts.ob_forum_demo.controllers;

import com.valts.ob_forum_demo.dto.PreguntaAndRespuestaUserVotosDTO;
import com.valts.ob_forum_demo.dto.PreguntaWithUserAndVotosDTO;
import com.valts.ob_forum_demo.dto.PreguntaWithUserDTO;
import com.valts.ob_forum_demo.models.Pregunta;
import com.valts.ob_forum_demo.servicios.implementations.PreguntaServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PreguntaController {

    private PreguntaServiceImpl preguntaService;

    public PreguntaController(PreguntaServiceImpl preguntaService) {
        this.preguntaService = preguntaService;
    }


    @GetMapping("/foro/preguntas")
    public ResponseEntity<List<PreguntaWithUserDTO>> getAll(@RequestParam(required = false) Long temaId) {
        if (temaId != null) {
            List<PreguntaWithUserDTO> preguntas =  preguntaService.getPreguntasByTemaId(temaId);
            return ResponseEntity.ok(preguntas);
        }
        List<PreguntaWithUserDTO> preguntas = preguntaService.getPreguntas();
        return ResponseEntity.ok(preguntas);
    }

    @GetMapping("/foro/preguntas/{id}")
    public ResponseEntity<PreguntaAndRespuestaUserVotosDTO> getOne(@PathVariable Long id) {
        PreguntaAndRespuestaUserVotosDTO pregunta = preguntaService.getPreguntasById(id);
        return ResponseEntity.ok(pregunta);
    }


    @PostMapping("/foro/preguntas")
    public ResponseEntity<Pregunta> addOne(@RequestBody Pregunta pregunta) {
        Pregunta newPregunta =  preguntaService.addOne(pregunta);
        return ResponseEntity.ok(newPregunta);
    }

    @PutMapping("/foro/preguntas/{id}")
    public ResponseEntity<Pregunta> updateOne(@PathVariable Long id, @RequestBody Pregunta pregunta) {
        Pregunta updatedPregunta = preguntaService.updateOne(id, pregunta);
        return ResponseEntity.ok(updatedPregunta);
    }


    @DeleteMapping("/foro/preguntas/{id}")
    public ResponseEntity deleteOne(@PathVariable Long id)  {
        preguntaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/foro/preguntas")
    public ResponseEntity deleteAll() {
        preguntaService.deleteAll();
        return ResponseEntity.noContent().build();
    }






}
