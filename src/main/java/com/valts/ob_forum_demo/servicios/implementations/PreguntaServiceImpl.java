package com.valts.ob_forum_demo.servicios.implementations;


import com.valts.ob_forum_demo.dto.*;
import com.valts.ob_forum_demo.models.Pregunta;
import com.valts.ob_forum_demo.models.Respuesta;
import com.valts.ob_forum_demo.models.Tema;
import com.valts.ob_forum_demo.models.VotoRespuesta;
import com.valts.ob_forum_demo.repos.PreguntaRepository;
import com.valts.ob_forum_demo.servicios.PreguntaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

@Service
public class PreguntaServiceImpl implements PreguntaService {

    private PreguntaRepository preguntaRepo;

//    @Autowired
//    private RespuestaServiceImpl respuestaService;

    @Autowired
    private TemaServiceImpl temaService;

//    public PreguntaServiceImpl(PreguntaRepository preguntaRepo, RespuestaServiceImpl respuestaService) {
//        this.preguntaRepo = preguntaRepo;
//        this.respuestaService = respuestaService;
//    }


    public PreguntaServiceImpl(PreguntaRepository preguntaRepo) {
        this.preguntaRepo = preguntaRepo;
    }

    @Override
    public List<Pregunta> findAll() {
        return preguntaRepo.findAllByOrderByIsPinnedDesc();
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
    public Pregunta addOne(Pregunta pregunta, Long temaId) {
            pregunta.setCreatedAt(LocalDateTime.now());
//        pregunta.setUser(); get from token i guess
        Tema tema = temaService.findOne(temaId); //throws error if null?
        pregunta.setTema(tema);
        Pregunta newPregunta = preguntaRepo.save(pregunta);
        return newPregunta;
    }

    @Override
    public Pregunta updateOne(Long id, Pregunta pregunta) {
        Optional<Pregunta> preguntaOpt = preguntaRepo.findById(id);
        if (preguntaOpt.isPresent()) {
            Pregunta newPregunta = preguntaOpt.get();
            newPregunta.setTitle(pregunta.getTitle());
            newPregunta.setDescription(pregunta.getDescription());
//            if admin
//              newPregunta.ispinned = true.false
            return preguntaRepo.save(newPregunta);

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

//    Some DTO YOLO
    @Override
    public List<PreguntaWithUserDTO> getPreguntas() {

        List<Pregunta> preguntas = preguntaRepo.findAllByOrderByIsPinnedDesc();
        List<PreguntaWithUserDTO> preguntasDto = new ArrayList<>();
        preguntas.forEach(pregunta -> {
            UserDTO userDto = new UserDTO();
            PreguntaWithUserDTO preguntaWithUserDto = new PreguntaWithUserDTO();

            preguntaWithUserDto.setId(pregunta.getId());
            preguntaWithUserDto.setTitle(pregunta.getTitle());
            preguntaWithUserDto.setDescription(pregunta.getDescription());
            preguntaWithUserDto.setCreatedAt(pregunta.getCreatedAt());
            preguntaWithUserDto.setPinned(pregunta.isPinned());
            preguntaWithUserDto.setTotalRespuestas(pregunta.getRespuestas().size());

            userDto.setId(pregunta.getUser().getId());
            userDto.setUsername(pregunta.getUser().getUsername());
            userDto.setAvatar(pregunta.getUser().getAvatar());
            preguntaWithUserDto.setUser(userDto);

            preguntasDto.add(preguntaWithUserDto);
        });

        return preguntasDto;
    }


    @Override
    public PreguntaAndRespuestaUserVotosDTO getPreguntasById(Long id) {
        Optional<Pregunta> preguntaOptional =  preguntaRepo.findById(id);
        if (preguntaOptional.isPresent()) {
            Pregunta pregunta = preguntaOptional.get();

            List<RespuestaWithUserAndVotosDTO> respuestasDTO = new ArrayList<>();
            PreguntaAndRespuestaUserVotosDTO preguntaAndRespuestaUserVotosDTO = new PreguntaAndRespuestaUserVotosDTO();

            preguntaAndRespuestaUserVotosDTO.setId(pregunta.getId());
            preguntaAndRespuestaUserVotosDTO.setTitle(pregunta.getTitle());
            preguntaAndRespuestaUserVotosDTO.setDescription(pregunta.getDescription());
            preguntaAndRespuestaUserVotosDTO.setCreatedAt(pregunta.getCreatedAt());
            preguntaAndRespuestaUserVotosDTO.setPinned(pregunta.isPinned());
            preguntaAndRespuestaUserVotosDTO.setTotalRespuestas(pregunta.getRespuestas().size());

            List<Respuesta> respuestas = pregunta.getRespuestas();
            respuestas.forEach(respuesta -> {
                respuestasDTO.add(respuestaDTOConverter(respuesta));
            });

            preguntaAndRespuestaUserVotosDTO.setRespuestas(respuestasDTO);


            return preguntaAndRespuestaUserVotosDTO;
        }

        return null;
    }

//    List<Tema> findAllByOrderByIsPinnedDesc();

    @Override
    public List<PreguntaWithUserDTO> getPreguntasByTemaId(Long id) {

        List<Pregunta> preguntas = preguntaRepo.findAllByTemaIdOrderByIsPinnedDesc(id);
        List<PreguntaWithUserDTO> preguntasDto = new ArrayList<>();
        preguntas.forEach(pregunta -> {
            UserDTO userDto = new UserDTO();
            PreguntaWithUserDTO preguntaWithUserDto = new PreguntaWithUserDTO();

            preguntaWithUserDto.setId(pregunta.getId());
            preguntaWithUserDto.setTitle(pregunta.getTitle());
            preguntaWithUserDto.setDescription(pregunta.getDescription());
            preguntaWithUserDto.setCreatedAt(pregunta.getCreatedAt());
            preguntaWithUserDto.setPinned(pregunta.isPinned());
            preguntaWithUserDto.setTotalRespuestas(pregunta.getRespuestas().size());

            userDto.setId(pregunta.getUser().getId());
            userDto.setUsername(pregunta.getUser().getUsername());
            userDto.setAvatar(pregunta.getUser().getAvatar());
            preguntaWithUserDto.setUser(userDto);

            preguntasDto.add(preguntaWithUserDto);
        });

        return preguntasDto;

    }

    public RespuestaWithUserAndVotosDTO respuestaDTOConverter(Respuesta respuesta) {
        UserDTO userDTO = new UserDTO();
        RespuestaWithUserAndVotosDTO respuestaWithUserAndVotosDTO = new RespuestaWithUserAndVotosDTO();
        respuestaWithUserAndVotosDTO.setId(respuesta.getId());
        respuestaWithUserAndVotosDTO.setRespuestaText(respuesta.getRespuestaText());
        respuestaWithUserAndVotosDTO.setCreatedAt(respuesta.getCreatedAt());
        respuestaWithUserAndVotosDTO.setPinned(respuesta.isPinned());

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
