package com.valts.ob_forum_demo.servicios.implementations;

import com.valts.ob_forum_demo.models.Pregunta;
import com.valts.ob_forum_demo.models.Respuesta;
import com.valts.ob_forum_demo.repos.RespuestaRepository;
import com.valts.ob_forum_demo.servicios.RespuestaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RespuestaServiceImpl implements RespuestaService {

    private RespuestaRepository respuestaRepo;

    public RespuestaServiceImpl(RespuestaRepository respuestaRepo) {
        this.respuestaRepo = respuestaRepo;
    }

    @Override
    public List<Respuesta> findAll() {
        return respuestaRepo.findAll();
    }

    @Override
    public Respuesta findOne(Long id) {
        Optional<Respuesta> respuestaOpt = respuestaRepo.findById(id);
        if (respuestaOpt.isPresent()) {
            return respuestaOpt.get();
        }

        return null;
    }

    @Override
    public Respuesta addOne(Respuesta respuesta) {
        Respuesta newRespuesta = respuestaRepo.save(respuesta);
        return newRespuesta;
    }

    @Override
    public Respuesta updateOne(Long id, Respuesta respuesta) {
        Optional<Respuesta> respuestaOpt = respuestaRepo.findById(id);
        if (respuestaOpt.isPresent()) {
            respuesta.setId(id);
            return respuestaRepo.save(respuesta);

        }
        return null;
    }

    @Override
    public void deleteById(Long id) {
        Optional<Respuesta> respuestaOpt = respuestaRepo.findById(id);
        if (respuestaOpt.isPresent()) {
            respuestaRepo.deleteById(id);
        }
    }

    @Override
    public void deleteAll() {
        respuestaRepo.deleteAll();
    }
}
