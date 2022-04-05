package com.valts.ob_forum_demo.controllers;

import com.valts.ob_forum_demo.dto.*;
import com.valts.ob_forum_demo.models.Pregunta;
import com.valts.ob_forum_demo.servicios.implementations.PreguntaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PreguntaController {

    @Autowired
    private PreguntaServiceImpl preguntaService;



    @GetMapping("/foro/preguntas")
    public ResponseEntity<List<PreguntaDTO>> getAll(@RequestParam(required = false) Long temaId) {
        if (temaId != null) {
            List<PreguntaDTO> preguntas = preguntaService.findAllByTemaId(temaId);
            return ResponseEntity.ok(preguntas);
        }
        List<PreguntaDTO> preguntas = preguntaService.findAll();
        return ResponseEntity.ok(preguntas);
    }

    @GetMapping("/foro/preguntas/{id}")
    public ResponseEntity<PreguntaRespuestaDTO> getOne(@PathVariable Long id) {
        PreguntaRespuestaDTO pregunta = preguntaService.findPreguntaRespuestaDTO(id);
//        PreguntaAndRespuestaUserVotosDTO pregunta = preguntaService.getPreguntasById(id);
        return ResponseEntity.ok(pregunta);
    }


    @PostMapping("/foro/preguntas")
    public ResponseEntity<Pregunta> addOne(@RequestParam Long temaId, @RequestBody Pregunta pregunta) {
        Pregunta newPregunta =  preguntaService.addOne(pregunta, temaId);
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
