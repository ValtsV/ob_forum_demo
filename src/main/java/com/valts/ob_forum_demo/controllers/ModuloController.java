package com.valts.ob_forum_demo.controllers;

import com.valts.ob_forum_demo.models.Modulo;
import com.valts.ob_forum_demo.servicios.implementations.ModuloServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ModuloController {

    private ModuloServiceImpl moduloService;

    public ModuloController(ModuloServiceImpl moduloService) {
        this.moduloService = moduloService;
    }

    @GetMapping("/foro/modulos")
    public ResponseEntity<List<Modulo>> getModulos() {
        List<Modulo> modulos = moduloService.findAll();
        return ResponseEntity.ok(modulos);
    }

    @GetMapping("/foro/modulos/{id}")
    public ResponseEntity<Modulo> getModulo(@PathVariable Long id) {
        Modulo modulo = moduloService.findOne(id);
        return ResponseEntity.ok(modulo);
    }


    @PostMapping("/foro/modulos")
    public ResponseEntity<Modulo> addModulo(@RequestBody Modulo modulo) {
        Modulo savedModulo =  moduloService.addModulo(modulo);
        return ResponseEntity.ok(savedModulo);
    }

    @PutMapping("/foro/modulos/{id}")
    public ResponseEntity<Modulo> updateModulo(@PathVariable Long id, @RequestBody Modulo modulo) {
        Modulo updatedModulo = moduloService.updateModulo(id, modulo);
        return ResponseEntity.ok(updatedModulo);
    }

    @DeleteMapping("/foro/modulos/{id}")
    public ResponseEntity deleteModulo(@PathVariable Long id)  {
        moduloService.deleteModulo(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/foro/modulos")
    public ResponseEntity deleteAllModulos() {
        moduloService.deleteAllModulos();
        return ResponseEntity.noContent().build();
    }
}
