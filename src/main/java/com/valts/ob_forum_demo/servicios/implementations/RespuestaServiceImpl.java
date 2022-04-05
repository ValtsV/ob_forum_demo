package com.valts.ob_forum_demo.servicios.implementations;

import com.valts.ob_forum_demo.dto.RespuestaDTOi;
import com.valts.ob_forum_demo.dto.RespuestaDTO;
import com.valts.ob_forum_demo.dto.UserDTO;
import com.valts.ob_forum_demo.models.*;
import com.valts.ob_forum_demo.repos.RespuestaRepository;
import com.valts.ob_forum_demo.servicios.RespuestaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RespuestaServiceImpl implements RespuestaService {

    @Autowired
    private RespuestaRepository respuestaRepo;

    @Autowired
    private PreguntaServiceImpl preguntaService;

    @Override
    public List<RespuestaDTO> findAll(Long preguntaId, String sort, String order) {
        List<RespuestaDTOi> respuestasDTO;
        List<RespuestaDTO> respuestaDTOList = new ArrayList<>();

        if(sort != null && sort.equals("totalVotosPositivos")) {
                respuestasDTO = respuestaRepo.findRespuestasUserVotosByPreguntaIdOrderedByPosVotes(preguntaId);

            respuestasDTO.forEach(respuesta -> {
                RespuestaDTO respuestaDTO = new RespuestaDTO();

                respuestaDTO.setId(respuesta.getId());
                respuestaDTO.setRespuestaText(respuesta.getRespuestaText());
                respuestaDTO.setTotalVotosNegativos(respuesta.getTotalVotosNegativos());
                respuestaDTO.setTotalVotosPositivos(respuesta.getTotalVotosPositivos());
                respuestaDTO.setCreatedAt(respuesta.getCreatedAt());
                respuestaDTO.setPinned(respuesta.getIsPinned());

                UserDTO userDTO = new UserDTO();
                userDTO.setId(respuesta.getUserId());
                userDTO.setUsername(respuesta.getUsername());
                userDTO.setAvatar(respuesta.getAvatar());

                respuestaDTO.setUser(userDTO);

                respuestaDTOList.add(respuestaDTO);
            });
            return respuestaDTOList;
        } else if(sort != null && sort.equals("updated_at")){
            if (order != null && order.equals("desc")) {
                respuestasDTO = respuestaRepo.findRespuestasUserVotosByPreguntaIdOrderedByCreatedAtDesc(preguntaId);

            } else  {
                respuestasDTO = respuestaRepo.findRespuestasUserVotosByPreguntaIdOrderedByCreatedAtAsc(preguntaId);
            }
            respuestasDTO.forEach(respuesta -> {
                RespuestaDTO respuestaDTO = new RespuestaDTO();

                respuestaDTO.setId(respuesta.getId());
                respuestaDTO.setRespuestaText(respuesta.getRespuestaText());
                respuestaDTO.setTotalVotosNegativos(respuesta.getTotalVotosNegativos());
                respuestaDTO.setTotalVotosPositivos(respuesta.getTotalVotosPositivos());
                respuestaDTO.setCreatedAt(respuesta.getCreatedAt());
                respuestaDTO.setPinned(respuesta.getIsPinned());

                UserDTO userDTO = new UserDTO();
                userDTO.setId(respuesta.getUserId());
                userDTO.setUsername(respuesta.getUsername());
                userDTO.setAvatar(respuesta.getAvatar());

                respuestaDTO.setUser(userDTO);

                respuestaDTOList.add(respuestaDTO);
            });
            return respuestaDTOList;
        }

        else {
            respuestasDTO = respuestaRepo.findRespuestasUserVotosByPreguntaId(preguntaId);


            respuestasDTO.forEach(respuesta -> {
                        RespuestaDTO respuestaDTO = new RespuestaDTO();

                        respuestaDTO.setId(respuesta.getId());
                        respuestaDTO.setRespuestaText(respuesta.getRespuestaText());
                        respuestaDTO.setTotalVotosNegativos(respuesta.getTotalVotosNegativos());
                        respuestaDTO.setTotalVotosPositivos(respuesta.getTotalVotosPositivos());
                        respuestaDTO.setCreatedAt(respuesta.getCreatedAt());
                        respuestaDTO.setPinned(respuesta.getIsPinned());

                        UserDTO userDTO = new UserDTO();
                        userDTO.setId(respuesta.getUserId());
                        userDTO.setUsername(respuesta.getUsername());
                        userDTO.setAvatar(respuesta.getAvatar());

                        respuestaDTO.setUser(userDTO);

                        respuestaDTOList.add(respuestaDTO);
                    });
                return respuestaDTOList;
        }
    }

    @Override
    public RespuestaDTO findOne(Long id) {
        RespuestaDTOi respuesta = respuestaRepo.findRespuestasUserVotosById(id);

            RespuestaDTO respuestaDTO = new RespuestaDTO();

            respuestaDTO.setId(respuesta.getId());
            respuestaDTO.setRespuestaText(respuesta.getRespuestaText());
            respuestaDTO.setTotalVotosNegativos(respuesta.getTotalVotosNegativos());
            respuestaDTO.setTotalVotosPositivos(respuesta.getTotalVotosPositivos());
            respuestaDTO.setCreatedAt(respuesta.getCreatedAt());
            respuestaDTO.setPinned(respuesta.getIsPinned());

            UserDTO userDTO = new UserDTO();
            userDTO.setId(respuesta.getUserId());
            userDTO.setUsername(respuesta.getUsername());
            userDTO.setAvatar(respuesta.getAvatar());

            respuestaDTO.setUser(userDTO);


        return respuestaDTO;
    }

    @Override
    public Respuesta addOne(Long preguntaId, Respuesta respuesta) {
//        respuesta.setUser(); get from token i guess

        Pregunta pregunta = preguntaService.findOne(preguntaId); //throws error if null?
        respuesta.setPregunta(pregunta);
        respuesta.setCreatedAt(LocalDateTime.now());
        return respuestaRepo.save(respuesta);
    }

    @Override
    public Respuesta updateOne(Long id, Respuesta respuesta) {
        Optional<Respuesta> respuestaOptional = respuestaRepo.findById(id);
        if (respuestaOptional.isPresent()) {
            Respuesta newRespuesta = respuestaOptional.get();
//            setupdatedAt
            newRespuesta.setRespuestaText(respuesta.getRespuestaText());
//            if admin
//              newRespuesta.ispinned = true.false
            return respuestaRepo.save(newRespuesta);

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

}
