package com.valts.ob_forum_demo.servicios.implementations;

import com.valts.ob_forum_demo.dto.RespuestaWithUserAndVotosDTO;
import com.valts.ob_forum_demo.dto.UserDTO;
import com.valts.ob_forum_demo.models.Respuesta;
import com.valts.ob_forum_demo.models.Voto;
import com.valts.ob_forum_demo.models.VotoRespuesta;
import com.valts.ob_forum_demo.repos.RespuestaRepository;
import com.valts.ob_forum_demo.servicios.RespuestaService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class RespuestaServiceImpl implements RespuestaService {

    private RespuestaRepository respuestaRepo;
    private VotoServiceImpl votoService;


    public RespuestaServiceImpl(RespuestaRepository respuestaRepo, VotoServiceImpl votoService) {
        this.respuestaRepo = respuestaRepo;
        this.votoService = votoService;
    }

    @Override
    public List<RespuestaWithUserAndVotosDTO> findAll(Long preguntaId) {
        List<Respuesta> respuestas = respuestaRepo.findByPregunta_Id(preguntaId);

        List<RespuestaWithUserAndVotosDTO> respuestaWithUserAndVotosDTOList = new ArrayList<>();

        respuestas.forEach(respuesta -> {
            RespuestaWithUserAndVotosDTO respuestaWithUserAndVotosDTO = respuestaDTOConverter(respuesta);
            respuestaWithUserAndVotosDTOList.add(respuestaWithUserAndVotosDTO);
        });


        return respuestaWithUserAndVotosDTOList;


    }

    @Override
    public RespuestaWithUserAndVotosDTO findOne(Long id) {
        Optional<Respuesta> respuestaOpt = respuestaRepo.findById(id);
        if (respuestaOpt.isPresent()) {
            Respuesta respuesta = respuestaOpt.get();
            return respuestaDTOConverter(respuesta);
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


    public RespuestaWithUserAndVotosDTO respuestaDTOConverter(Respuesta respuesta) {
        UserDTO userDTO = new UserDTO();
        RespuestaWithUserAndVotosDTO respuestaWithUserAndVotosDTO = new RespuestaWithUserAndVotosDTO();
        respuestaWithUserAndVotosDTO.setId(respuesta.getId());
        respuestaWithUserAndVotosDTO.setRespuestaText(respuesta.getRespuestaText());
        respuestaWithUserAndVotosDTO.setCreatedAt(respuesta.getCreatedAt());

        List<VotoRespuesta> votos = respuesta.getVotosRespuesta();
        Long positiveVotos = votos.stream().filter(voto -> voto.isVoto() == true).count();
        Long negativeVotos = votos.size() - positiveVotos;


        respuestaWithUserAndVotosDTO.setTotalVotosPositivos(positiveVotos);
        respuestaWithUserAndVotosDTO.setTotalVotosNegativos(negativeVotos);

        userDTO.setId(respuesta.getUser().getId());
        userDTO.setUsername(respuesta.getUser().getUsername());
        userDTO.setAvatar(respuesta.getUser().getAvatar());

        respuestaWithUserAndVotosDTO.setUser(userDTO);
        return  respuestaWithUserAndVotosDTO;
    }
}
