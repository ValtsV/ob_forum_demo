package com.valts.ob_forum_demo.servicios.implementations;

import com.valts.ob_forum_demo.dto.VotoDTO;
import com.valts.ob_forum_demo.models.*;
import com.valts.ob_forum_demo.repos.*;
import com.valts.ob_forum_demo.servicios.VotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VotoServiceImpl implements VotoService {

    @Autowired
    private VotoRepository votoRepo;
    @Autowired
    private PreguntaRepository preguntaRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RespuestaRepository respuestaRepository;



    @Override
    public List<VotoPregunta> findAllByPreguntaId(Long id) {

        List<VotoPregunta> votos =  votoRepo.findAllByPregunta_Id(id);

        return votos;
    }

    @Override
    public List<VotoRespuesta> findAllByRespuestaId(Long id) {

        List<VotoRespuesta> votos =  votoRepo.findAllByRespuesta_Id(id);

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
    public Voto addVotoRespuesta(Long id, VotoDTO voto) {
        Optional<Respuesta> respuestaOptional = respuestaRepository.findById(id);
        VotoRespuesta votoRespuesta = new VotoRespuesta();

        if (respuestaOptional.isPresent()) {
            votoRespuesta.setRespuesta(respuestaOptional.get());
            votoRespuesta.setVoto(voto.isVoto());
//            set user form token or smth
        }
        Voto savedVoto = votoRepo.save(votoRespuesta);

        return savedVoto;
    }

    @Override
    public Voto addVotoPregunta(Long id, VotoDTO voto) {
        Optional<Pregunta> preguntaOptional = preguntaRepository.findById(id);
        VotoPregunta votoPregunta = new VotoPregunta();
        if (preguntaOptional.isPresent()) {
            votoPregunta.setPregunta(preguntaOptional.get());
            votoPregunta.setVoto(voto.isVoto());
//            set user from token or smth
        }
        Voto savedVoto = votoRepo.save(votoPregunta);

        return savedVoto;
    }



    @Override
    public Voto updateOne(Long id) {
        Optional<Voto> votoOptional = votoRepo.findById(id);
        Voto voto = votoOptional.get();
        if (voto instanceof VotoRespuesta) {
            VotoRespuesta votoRespuesta = (VotoRespuesta) voto;
            votoRespuesta.setVoto(!voto.isVoto());
            voto = votoRepo.save(votoRespuesta);
        } else {
            VotoPregunta votoPregunta = (VotoPregunta) voto;
            votoPregunta.setVoto(!voto.isVoto());
            voto = votoRepo.save(votoPregunta);
        }

        return voto;
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


}
