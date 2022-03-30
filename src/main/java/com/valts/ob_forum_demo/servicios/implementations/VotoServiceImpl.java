package com.valts.ob_forum_demo.servicios.implementations;

import com.valts.ob_forum_demo.dto.VotoDTO;
import com.valts.ob_forum_demo.models.*;
import com.valts.ob_forum_demo.repos.*;
import com.valts.ob_forum_demo.servicios.VotoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class VotoServiceImpl implements VotoService {

    private VotoRepository votoRepo;
    private VotoPreguntaRepository votoPreguntaRepository;
    private VotoRespuestaRepository votoRespuestaRepository;
    private PreguntaRepository preguntaRepository;
    private UserRepository userRepository;


//    public VotoServiceImpl(VotoRepository votoRepo) {
//        this.votoRepo = votoRepo;
//    }


    public VotoServiceImpl(VotoRepository votoRepo, VotoPreguntaRepository votoPreguntaRepository, VotoRespuestaRepository votoRespuestaRepository, PreguntaRepository preguntaRepository, UserRepository userRepository) {
        this.votoRepo = votoRepo;
        this.votoPreguntaRepository = votoPreguntaRepository;
        this.votoRespuestaRepository = votoRespuestaRepository;
        this.preguntaRepository = preguntaRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Voto addVoto(Voto voto) {
        Voto savedVoto = votoRepo.save(voto);

        return voto;
    }

    @Override
    public List<VotoPregunta> findAllByPreguntaId(Long id) {

        List<VotoPregunta> votos =  votoPreguntaRepository.findAllByPregunta_Id(id);

        return votos;
    }

    @Override
    public List<VotoRespuesta> findAllByRespuestaId(Long id) {

        List<VotoRespuesta> votos =  votoRespuestaRepository.findAllByRespuesta_Id(id);

        return votos;
    }

    @Override
    public Voto findOne(Long id) {
        Optional<Voto> votoOptional = votoRepo.findById(id);
        if (votoOptional.isPresent()) {
            Voto voto = votoOptional.get();
            if (voto instanceof VotoRespuesta) {
                VotoRespuesta votoRespuesta = (VotoRespuesta) voto;
                return votoRespuesta;
            }
            VotoPregunta votoPregunta = (VotoPregunta) voto;
            return votoPregunta;
        }
        return null;
    }



    @Override
    public Voto addOne(VotoDTO votoDTO) {
        VotoPregunta votoPregunta = new VotoPregunta();
        Optional<Pregunta> preguntaOptional = preguntaRepository.findById(votoDTO.getPreguntaId());
        Optional<User> userOptional = userRepository.findById(votoDTO.getUserId());
        if (preguntaOptional.isPresent() && userOptional.isPresent()) {
            User user = userOptional.get();
            Pregunta pregunta = preguntaOptional.get();

            votoPregunta.setUser(user);
            votoPregunta.setPregunta(pregunta);
            votoPregunta.setVoto(votoDTO.isVoto());
            Voto savedVoto = votoRepo.save(votoPregunta);
            return savedVoto;
        }

        return null;
    }

    @Override
    public Voto updateOne(Long id, Voto voto) {
        Optional<Voto> tt = votoRepo.findById(id);
        Voto lolvoto = tt.get();
        if (lolvoto instanceof VotoRespuesta) {
            VotoRespuesta yo = (VotoRespuesta) lolvoto;
            yo.setVoto(voto.isVoto());
            votoRepo.save(yo);
        } else {
            VotoPregunta yo = (VotoPregunta) lolvoto;
            yo.setVoto(voto.isVoto());
            votoRepo.save(yo);
        }

        return null;
    }

    @Override
    public void deleteOne(Long id) {
        Optional<Voto> votoOptional = votoRepo.findById(id);
        if (votoOptional.isPresent()) {
            votoRepo.delete(votoOptional.get());
        }
    }

    @Override
    public void deleteAll() {
        votoRepo.deleteAll();
    }

    @Override
    public HashMap<String, Integer> getVotosByRespuestaId(Long respuestaId){

        List<VotoRespuesta> votos = votoRespuestaRepository.findAllByRespuesta_Id(respuestaId);
        Integer positives = votoRespuestaRepository.countByVotoAndRespuestaId(true, respuestaId);;
        Integer negatives = votoRespuestaRepository.countByVotoAndRespuestaId(false, respuestaId);;

            HashMap<String, Integer> votes = new HashMap<>();
            votes.put("Positives", positives);
            votes.put("Negatives", negatives);
            return votes;
        }
}
