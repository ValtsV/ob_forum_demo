package com.valts.ob_forum_demo.controllers;

import com.valts.ob_forum_demo.models.Pregunta;
import com.valts.ob_forum_demo.models.Respuesta;
import com.valts.ob_forum_demo.servicios.RespuestaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RespuestaController {

    private RespuestaService respuestaService;

    public RespuestaController(RespuestaService respuestaService) {
        this.respuestaService = respuestaService;
    }

    @GetMapping("/foro/respuestas")
    public ResponseEntity<List<Respuesta>> getAll() {
        List<Respuesta> respuestas = respuestaService.findAll();
        return ResponseEntity.ok(respuestas);
    }

    @GetMapping("/foro/respuestas/{id}")
    public ResponseEntity<Respuesta> getOne(@PathVariable Long id) {
        Respuesta respuesta = respuestaService.findOne(id);
        return ResponseEntity.ok(respuesta);
    }


    @PostMapping("/foro/respuestas")
    public ResponseEntity<Respuesta> addOne(@RequestBody Respuesta respuesta) {
        Respuesta newRespuesta =  respuestaService.addOne(respuesta);
        return ResponseEntity.ok(newRespuesta);
    }

    @PutMapping("/foro/respuestas/{id}")
    public ResponseEntity<Respuesta> updateOne(@PathVariable Long id, @RequestBody Respuesta respuesta) {
        Respuesta updatedRespuesta = respuestaService.updateOne(id, respuesta);
        return ResponseEntity.ok(updatedRespuesta);
    }


    @DeleteMapping("/foro/respuestas/{id}")
    public ResponseEntity deleteOne(@PathVariable Long id)  {
        respuestaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/foro/respuestas")
    public ResponseEntity deleteAll() {
        respuestaService.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
