package com.valts.ob_forum_demo.controllers;

import com.valts.ob_forum_demo.dto.VotoDTO;
import com.valts.ob_forum_demo.models.Voto;
import com.valts.ob_forum_demo.models.VotoPregunta;
import com.valts.ob_forum_demo.models.VotoRespuesta;
import com.valts.ob_forum_demo.servicios.implementations.VotoServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VotoController {

        private VotoServiceImpl votoService;

    public VotoController(VotoServiceImpl votoService) {
        this.votoService = votoService;
    }


    @GetMapping("/foro/votos")
    public ResponseEntity<List<? extends Voto>> getVotosFilteredById(@RequestParam(required = false) Long preguntaId, @RequestParam(required = false) Long respuestaId) {


        if (preguntaId !=null && respuestaId == null) {
            List<VotoPregunta> votos =  votoService.findAllByPreguntaId(preguntaId);
            return ResponseEntity.ok(votos);
        }

        if(respuestaId !=null && preguntaId == null) {
            List<VotoRespuesta> votos = votoService.findAllByRespuestaId(respuestaId);
            return ResponseEntity.ok(votos);
        }
//        TODO: return all votos or smth
        return ResponseEntity.badRequest().build();

    }

    @GetMapping("/foro/votos/{id}")
    public ResponseEntity<Voto> getVotoById(@PathVariable Long id) {
        Voto voto = votoService.findOne(id);
        return ResponseEntity.ok(voto);
    }

    @PostMapping("/foro/votos")
    public ResponseEntity<Voto> addVoto(@RequestParam(required = false) Long preguntaId, @RequestParam(required = false) Long respuestaId, @RequestBody VotoDTO voto) {
        if (preguntaId == null && respuestaId == null) {
                return ResponseEntity.badRequest().build();
            }
            if (preguntaId != null && respuestaId != null) {
                return ResponseEntity.badRequest().build();
            }

            if (respuestaId != null) {
                Voto addedVoto = votoService.addVotoRespuesta(respuestaId, voto);
                return ResponseEntity.ok(addedVoto);
        }
            if(preguntaId != null) {
                Voto addedVoto = votoService.addVotoPregunta(preguntaId, voto);
                return ResponseEntity.ok(addedVoto);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("foro/votos/{id}")
    public ResponseEntity<Voto> updateVoto(@PathVariable Long id) {
        Voto voto = votoService.updateOne(id);
        return ResponseEntity.ok(voto);
    }

    @DeleteMapping("foro/votos/{id}")
    public ResponseEntity deleteVoto(@PathVariable Long id) {
        votoService.deleteOne(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("foro/votos")
    public ResponseEntity deleteAll() {
        votoService.deleteAll();
        return ResponseEntity.ok().build();
    }
}
