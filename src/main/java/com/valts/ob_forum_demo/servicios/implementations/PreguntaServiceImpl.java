package com.valts.ob_forum_demo.servicios.implementations;


import com.valts.ob_forum_demo.dto.*;
import com.valts.ob_forum_demo.models.*;
import com.valts.ob_forum_demo.repos.PreguntaRepository;
import com.valts.ob_forum_demo.repos.RespuestaRepository;
import com.valts.ob_forum_demo.servicios.PreguntaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

@Service
public class PreguntaServiceImpl implements PreguntaService {

    @Autowired
    private PreguntaRepository preguntaRepo;

    @Autowired
    @Lazy //avoid circular, check if better option is available
    private RespuestaServiceImpl respuestaService;

    @Autowired
    private RespuestaRepository respuestaRepo;

    @Autowired
    private TemaServiceImpl temaService;




    @Override
    public List<PreguntaDTO> findAll() {
//        List<PreguntaDTOi> preguntaDTOiList = preguntaRepo.findPreguntasUsersVotosOrderedByIsPinned();
        List<PreguntaDTOi> preguntaDTOiList = preguntaRepo.findPreguntasUsersVotosOrderedByIsPinned();
        List<PreguntaDTO> preguntaDTOList = new ArrayList<>();

        preguntaDTOiList.forEach(pregunta -> {
            PreguntaDTO preguntaDTO = new PreguntaDTO();
            UserDTO userDTO = new UserDTO();

            preguntaDTO.setId(pregunta.getId());
            preguntaDTO.setCreatedAt(pregunta.getCreatedAt());
            preguntaDTO.setPinned(pregunta.getIsPinned());
            preguntaDTO.setDescription(pregunta.getDescription());
            preguntaDTO.setTitle(pregunta.getTitle());
            preguntaDTO.setTemaId(pregunta.getTemaId());
            preguntaDTO.setTotalVotosPositivos(pregunta.getTotalVotosPositivos());
            preguntaDTO.setTotalVotosNegativos(pregunta.getTotalVotosNegativos());
            preguntaDTO.setTotalRespuestas(pregunta.getTotalRespuestas());

            userDTO.setId(pregunta.getUserId());
            userDTO.setUsername(pregunta.getUserUsername());
            userDTO.setAvatar(pregunta.getUserAvatar());
            preguntaDTO.setUser(userDTO);

            preguntaDTOList.add(preguntaDTO);
        });

        return preguntaDTOList;
    }

    @Override
    public List<PreguntaDTO> findAllByTemaId(Long id) {
        List<PreguntaDTOi> preguntaDTOiList = preguntaRepo.findPreguntasUsersVotosByTemaIdOrderedByIsPinned(id);
        List<PreguntaDTO> preguntaDTOList = new ArrayList<>();

        preguntaDTOiList.forEach(pregunta -> {
            PreguntaDTO preguntaDTO = new PreguntaDTO();
            UserDTO userDTO = new UserDTO();

            preguntaDTO.setId(pregunta.getId());
            preguntaDTO.setCreatedAt(pregunta.getCreatedAt());
            preguntaDTO.setPinned(pregunta.getIsPinned());
            preguntaDTO.setDescription(pregunta.getDescription());
            preguntaDTO.setTitle(pregunta.getTitle());
            preguntaDTO.setTemaId(pregunta.getTemaId());
            preguntaDTO.setTotalVotosNegativos(pregunta.getTotalVotosNegativos());
            preguntaDTO.setTotalVotosPositivos(pregunta.getTotalVotosPositivos());
            preguntaDTO.setTotalRespuestas(pregunta.getTotalRespuestas());

            userDTO.setId(pregunta.getUserId());
            userDTO.setUsername(pregunta.getUserUsername());
            userDTO.setAvatar(pregunta.getUserAvatar());
            preguntaDTO.setUser(userDTO);

            preguntaDTOList.add(preguntaDTO);
        });

        return preguntaDTOList;
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
    public PreguntaRespuestaDTO findPreguntaRespuestaDTO(Long id) {
        Optional<PreguntaDTOi> preguntaOpt = preguntaRepo.findPreguntaUserTotalVotosByPreguntaId(id);
        if (preguntaOpt.isPresent()) {
            PreguntaDTOi preguntaDTOi = preguntaOpt.get();

            UserDTO userDTO = new UserDTO();
            userDTO.setId(preguntaDTOi.getUserId());
            userDTO.setUsername(preguntaDTOi.getUserUsername());
            userDTO.setAvatar(preguntaDTOi.getUserAvatar());

            PreguntaRespuestaDTO preguntaRespuestaDTO = new PreguntaRespuestaDTO();
            preguntaRespuestaDTO.setId(preguntaDTOi.getId());
            preguntaRespuestaDTO.setCreatedAt(preguntaDTOi.getCreatedAt());
            preguntaRespuestaDTO.setTitle(preguntaDTOi.getTitle());
            preguntaRespuestaDTO.setDescription(preguntaDTOi.getDescription());
            preguntaRespuestaDTO.setPinned(preguntaDTOi.getIsPinned());
            preguntaRespuestaDTO.setTemaId(preguntaDTOi.getTemaId());
            preguntaRespuestaDTO.setTotalVotosNegativos(preguntaDTOi.getTotalVotosNegativos());
            preguntaRespuestaDTO.setTotalVotosPositivos(preguntaDTOi.getTotalVotosPositivos());
            preguntaRespuestaDTO.setTotalRespuestas(preguntaDTOi.getTotalRespuestas()); ////??????

            List<RespuestaDTO> respuestas =  respuestaService.findAll(id, null, null);

            preguntaRespuestaDTO.setRespuestas(respuestas);

            return preguntaRespuestaDTO;
        }


        return null;
    }


    @Override
    public Pregunta addOne(Pregunta pregunta, Long temaId) {
            pregunta.setCreatedAt(LocalDateTime.now());
//        pregunta.setUser(); get from token i guess
        Tema tema = temaService.findOne(temaId); //throws error if null?
        pregunta.setTema(tema);
        return preguntaRepo.save(pregunta);
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

}
