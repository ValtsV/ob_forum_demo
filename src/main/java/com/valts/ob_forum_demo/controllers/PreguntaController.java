package com.valts.ob_forum_demo.controllers;

import com.valts.ob_forum_demo.models.Pregunta;
import com.valts.ob_forum_demo.models.User;
import com.valts.ob_forum_demo.servicios.PreguntaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class PreguntaController {

    private PreguntaService preguntaService;

    @GetMapping("/foro/preguntas")
    public ResponseEntity<List<Pregunta>> getAll() {
        List<Pregunta> preguntas = preguntaService.findAll();
        return ResponseEntity.ok(preguntas);
    }

    @GetMapping("/foro/preguntas/{id}")
    public ResponseEntity<Pregunta> getOne(@PathVariable Long id) {
        Pregunta pregunta = preguntaService.findOne(id);
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
