package com.valts.ob_forum_demo.servicios.implementations;


import com.valts.ob_forum_demo.dto.*;
import com.valts.ob_forum_demo.models.Pregunta;
import com.valts.ob_forum_demo.models.Respuesta;
import com.valts.ob_forum_demo.repos.PreguntaRepository;
import com.valts.ob_forum_demo.servicios.PreguntaService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

@Service
public class PreguntaServiceImpl implements PreguntaService {

    private PreguntaRepository preguntaRepo;
    private RespuestaServiceImpl respuestaService;

    public PreguntaServiceImpl(PreguntaRepository preguntaRepo, RespuestaServiceImpl respuestaService) {
        this.preguntaRepo = preguntaRepo;
        this.respuestaService = respuestaService;
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

//    Some DTO YOLO
    @Override
    public List<PreguntaWithUserDTO> getPreguntas() {
        UserDTO userDto = new UserDTO();
        PreguntaWithUserDTO preguntaWithUserDto = new PreguntaWithUserDTO();
        List<Pregunta> preguntas = preguntaRepo.findAll();
        List<PreguntaWithUserDTO> preguntasDto = new ArrayList<>();
        preguntas.forEach(pregunta -> {
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
                respuestasDTO.add(respuestaService.respuestaDTOConverter(respuesta));
            });

            preguntaAndRespuestaUserVotosDTO.setRespuestas(respuestasDTO);


            return preguntaAndRespuestaUserVotosDTO;
        }

        return null;
    }


    @Override
    public List<PreguntaWithUserDTO> getPreguntasByTemaId(Long id) {
        UserDTO userDto = new UserDTO();
        PreguntaWithUserDTO preguntaWithUserDto = new PreguntaWithUserDTO();
        List<Pregunta> preguntas = preguntaRepo.findAllByTemaId(id);
        List<PreguntaWithUserDTO> preguntasDto = new ArrayList<>();
        preguntas.forEach(pregunta -> {
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
}
