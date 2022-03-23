package com.valts.ob_forum_demo.servicios.implementations;

import com.valts.ob_forum_demo.controllers.PreguntaController;
import com.valts.ob_forum_demo.models.Pregunta;
import com.valts.ob_forum_demo.models.User;
import com.valts.ob_forum_demo.repos.PreguntaRepository;
import com.valts.ob_forum_demo.servicios.PreguntaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PreguntaServiceImpl implements PreguntaService {

    private PreguntaRepository preguntaRepo;


    public PreguntaServiceImpl(PreguntaRepository preguntaRepo) {
        this.preguntaRepo = preguntaRepo;
    }

    @Override
    public List<Pregunta> findAll() {
        return preguntaRepo.findAll();
    }

    @Override
    public Pregunta findOne(Long id) {
        Optional<Pregunta> preguntaOpt = preguntaRepo.findById(id);
        if (preguntaOpt.isPresent()) {
            return preguntaOpt.get();
//            return dto pregunta + totalRespuestas
        }

        return null;
    }

    @Override
    public Pregunta addOne(Pregunta pregunta) {
        Pregunta newPregunta = preguntaRepo.save(pregunta);
        return newPregunta;
    }

    @Override
    public Pregunta updateOne(Long id, Pregunta pregunta) {
        Optional<Pregunta> preguntaOpt = preguntaRepo.findById(id);
        if (preguntaOpt.isPresent()) {
            pregunta.setId(id);
            return preguntaRepo.save(pregunta);

        }
        return null;
    }

    @Override
    public void deleteById(Long id) {
        Optional<Pregunta> preguntaOpt = preguntaRepo.findById(id);
        if (preguntaOpt.isPresent()) {
            preguntaRepo.deleteById(id);
        }
    }

    @Override
    public void deleteAll() {
        preguntaRepo.deleteAll();
    }
}
