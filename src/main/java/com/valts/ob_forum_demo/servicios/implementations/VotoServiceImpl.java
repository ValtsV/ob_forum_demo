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

        return votoRepo.findAllByPregunta_Id(id);
    }

    @Override
    public List<VotoRespuesta> findAllByRespuestaId(Long id) {

        return votoRepo.findAllByRespuesta_Id(id);
    }

    @Override
    public Voto findOne(Long id) {
        Optional<Voto> votoOptional = votoRepo.findById(id);
        if (votoOptional.isPresent()) {
            Voto voto = votoOptional.get();
            if (voto instanceof VotoRespuesta) {
                return voto;
            }
            return voto;
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

        return votoRepo.save(votoRespuesta);
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

        return votoRepo.save(votoPregunta);
    }



    @Override
    public Voto updateOne(Long id) {
        Optional<Voto> votoOptional = votoRepo.findById(id);
        if (votoOptional.isPresent()){
            Voto voto = votoOptional.get();
            if (voto instanceof VotoRespuesta votoRespuesta) {
                votoRespuesta.setVoto(!voto.isVoto());
                voto = votoRepo.save(votoRespuesta);
            } else {
                VotoPregunta votoPregunta = (VotoPregunta) voto;
                votoPregunta.setVoto(!voto.isVoto());
                voto = votoRepo.save(votoPregunta);
            }

            return voto;
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


}
