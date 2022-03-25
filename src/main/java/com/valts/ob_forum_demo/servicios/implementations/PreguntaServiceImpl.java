package com.valts.ob_forum_demo.servicios.implementations;

import com.valts.ob_forum_demo.controllers.PreguntaController;
import com.valts.ob_forum_demo.dto.PreguntaDTO;
import com.valts.ob_forum_demo.dto.PreguntaWithUserAndVotosDTO;
import com.valts.ob_forum_demo.dto.PreguntaWithUserDTO;
import com.valts.ob_forum_demo.dto.UserDTO;
import com.valts.ob_forum_demo.models.Pregunta;
import com.valts.ob_forum_demo.models.User;
import com.valts.ob_forum_demo.repos.PreguntaRepository;
import com.valts.ob_forum_demo.repos.VotoPreguntaRepository;
import com.valts.ob_forum_demo.servicios.PreguntaService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PreguntaServiceImpl implements PreguntaService {

    private PreguntaRepository preguntaRepo;
    private VotoPreguntaRepository votoPreguntaRepo;


    public PreguntaServiceImpl(PreguntaRepository preguntaRepo, VotoPreguntaRepository votoPreguntaRepo) {
        this.preguntaRepo = preguntaRepo;
        this.votoPreguntaRepo = votoPreguntaRepo;
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

// add respuestas en main and put this in controller and run postman
    public PreguntaWithUserAndVotosDTO getPreguntasById(Long id) {
        UserDTO userDto = new UserDTO();
        PreguntaWithUserAndVotosDTO preguntaWithUserAndVotosDto = new PreguntaWithUserAndVotosDTO();

        Optional<Pregunta> preguntaOpt = preguntaRepo.findById(id);
        if (preguntaOpt.isPresent()) {
            Pregunta pregunta = preguntaOpt.get();

            preguntaWithUserAndVotosDto.setId(pregunta.getId());
            preguntaWithUserAndVotosDto.setTitle(pregunta.getTitle());
            preguntaWithUserAndVotosDto.setDescription(pregunta.getDescription());
            preguntaWithUserAndVotosDto.setCreatedAt(pregunta.getCreatedAt());
            preguntaWithUserAndVotosDto.setPinned(pregunta.isPinned());
            preguntaWithUserAndVotosDto.setTotalRespuestas(pregunta.getRespuestas().size());

            userDto.setId(pregunta.getUser().getId());
            userDto.setUsername(pregunta.getUser().getUsername());
            userDto.setAvatar(pregunta.getUser().getAvatar());
            preguntaWithUserAndVotosDto.setUser(userDto);

            return preguntaWithUserAndVotosDto;
        }

        return null;
    }

//    @Override
//    public List<PreguntaWithUserAndVotosDTO> getPreguntasById(Long id) {
//        UserDTO userDto = new UserDTO();
//        PreguntaWithUserAndVotosDTO preguntaWithUserAndVotosDto = new PreguntaWithUserAndVotosDTO();
//
//    }
}
