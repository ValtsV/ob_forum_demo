package com.valts.ob_forum_demo.servicios.implementations;

import com.valts.ob_forum_demo.models.VotoPregunta;
import com.valts.ob_forum_demo.repos.VotoPreguntaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class VotoPreguntaServiceImpl {

    private VotoPreguntaRepository votoPreguntaRepo;

    public VotoPreguntaServiceImpl(VotoPreguntaRepository votoPreguntaRepo) {
        this.votoPreguntaRepo = votoPreguntaRepo;
    }

        public HashMap<String, Long> getVotosCount(List<VotoPregunta> votos) {
        Long a;
        Long b;

        a = votos.stream().filter(voto -> voto.isVoto() == true).count();
        b = votos.size() - a;

//        probs cast to int
            HashMap<String, Long> votosResults = new HashMap<>();
            votosResults.put("Positives", a);
            votosResults.put("Negatives", b);

        return  votosResults;

    }

    public List<VotoPregunta> getVotosByPreguntaId(Long id) {

        return votoPreguntaRepo.findByPregunta_IdEquals(id);
    }
}
