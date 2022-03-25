package com.valts.ob_forum_demo.servicios.implementations;

import com.valts.ob_forum_demo.models.VotoRespuesta;
import com.valts.ob_forum_demo.repos.VotoRespuestaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VotoRespuestaServiceImpl {

    private VotoRespuestaRepository votoRespuestaRepo;

    public VotoRespuestaServiceImpl(VotoRespuestaRepository votoRespuestaRepo) {
        this.votoRespuestaRepo = votoRespuestaRepo;
    }

//    public smth getVotosCount(List<VotoRespuesta> votos) {
//        Integer a;
//        Integer b;
//
//
//
//    }
//
//    public List<VotoRespuesta> getVotosByRespuestaId(Long id) {
//
//        return votoRespuestaRepo.findAllByRespuestaId(id);
//    }
}
