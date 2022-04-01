package com.valts.ob_forum_demo.servicios.implementations;

import com.valts.ob_forum_demo.dto.Lolz;
import com.valts.ob_forum_demo.dto.RespuestaDTO;
import com.valts.ob_forum_demo.dto.RespuestaWithUserAndVotosDTO;
import com.valts.ob_forum_demo.dto.UserDTO;
import com.valts.ob_forum_demo.models.*;
import com.valts.ob_forum_demo.repos.RespuestaRepository;
import com.valts.ob_forum_demo.servicios.RespuestaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class RespuestaServiceImpl implements RespuestaService {

    private RespuestaRepository respuestaRepo;
    private VotoServiceImpl votoService;

    @Autowired
    private PreguntaServiceImpl preguntaService;


    public RespuestaServiceImpl(RespuestaRepository respuestaRepo, VotoServiceImpl votoService) {
        this.respuestaRepo = respuestaRepo;
        this.votoService = votoService;
    }
//    findAllByOrderByIsPinnedDesc

//    public List<RespuestaWithUserAndVotosDTO> findAll

    @Override
    public List<RespuestaWithUserAndVotosDTO> findAll(Long preguntaId, String sort, String order) {
        List<Respuesta> respuestas = new ArrayList<>();
        List<RespuestaDTO> respuestasDTO = new ArrayList<>();
        List<RespuestaWithUserAndVotosDTO> respuestaWithUserAndVotosDTOList = new ArrayList<>();

        if(sort != null && sort.equals("totalVotosPositivos")) {
                respuestasDTO = respuestaRepo.findRespuestasUserVotosByPreguntaIdOrderedByPosVotes(preguntaId);

//            List<RespuestaWithUserAndVotosDTO> respuestaWithUserAndVotosDTOList = new ArrayList<>();
            respuestasDTO.forEach(respuesta -> {
                RespuestaWithUserAndVotosDTO respuestaWithUserAndVotosDTO = new RespuestaWithUserAndVotosDTO();

                respuestaWithUserAndVotosDTO.setId(respuesta.getId());
                respuestaWithUserAndVotosDTO.setRespuestaText(respuesta.getRespuestaText());
                respuestaWithUserAndVotosDTO.setTotalVotosNegativos(respuesta.getTotalVotosNegativos());
                respuestaWithUserAndVotosDTO.setTotalVotosPositivos(respuesta.getTotalVotosPositivos());
                respuestaWithUserAndVotosDTO.setCreatedAt(respuesta.getCreatedAt());
                respuestaWithUserAndVotosDTO.setPinned(respuesta.getIsPinned());

                UserDTO userDTO = new UserDTO();
                userDTO.setId(respuesta.getUserId());
                userDTO.setUsername(respuesta.getUsername());
                userDTO.setAvatar(respuesta.getAvatar());

                respuestaWithUserAndVotosDTO.setUser(userDTO);

                respuestaWithUserAndVotosDTOList.add(respuestaWithUserAndVotosDTO);
            });
            return respuestaWithUserAndVotosDTOList;
//            respuestas = respuestaRepo.findByPregunta_IdOrderByIsPinnedDesc(preguntaId, Sort.by(Sort.Direction.DESC, sort));
        } else if(sort != null && sort.equals("updated_at")){
            if (order != null && order.equals("desc")) {
                respuestasDTO = respuestaRepo.findRespuestasUserVotosByPreguntaIdOrderedByCreatedAtDesc(preguntaId);

            } else  {
                respuestasDTO = respuestaRepo.findRespuestasUserVotosByPreguntaIdOrderedByCreatedAtAsc(preguntaId);
            }
//            List<RespuestaWithUserAndVotosDTO> respuestaWithUserAndVotosDTOList = new ArrayList<>();
            respuestasDTO.forEach(respuesta -> {
                RespuestaWithUserAndVotosDTO respuestaWithUserAndVotosDTO = new RespuestaWithUserAndVotosDTO();

                respuestaWithUserAndVotosDTO.setId(respuesta.getId());
                respuestaWithUserAndVotosDTO.setRespuestaText(respuesta.getRespuestaText());
                respuestaWithUserAndVotosDTO.setTotalVotosNegativos(respuesta.getTotalVotosNegativos());
                respuestaWithUserAndVotosDTO.setTotalVotosPositivos(respuesta.getTotalVotosPositivos());
                respuestaWithUserAndVotosDTO.setCreatedAt(respuesta.getCreatedAt());
                respuestaWithUserAndVotosDTO.setPinned(respuesta.getIsPinned());

                UserDTO userDTO = new UserDTO();
                userDTO.setId(respuesta.getUserId());
                userDTO.setUsername(respuesta.getUsername());
                userDTO.setAvatar(respuesta.getAvatar());

                respuestaWithUserAndVotosDTO.setUser(userDTO);

                respuestaWithUserAndVotosDTOList.add(respuestaWithUserAndVotosDTO);
            });
            return respuestaWithUserAndVotosDTOList;
        }

        else {
//            respuestas = respuestaRepo.findByPregunta_IdOrderByIsPinnedDesc(preguntaId);
            respuestasDTO = respuestaRepo.findRespuestasUserVotosByPreguntaId(preguntaId);


            respuestasDTO.forEach(respuesta -> {
                        RespuestaWithUserAndVotosDTO respuestaWithUserAndVotosDTO = new RespuestaWithUserAndVotosDTO();

                        respuestaWithUserAndVotosDTO.setId(respuesta.getId());
                        respuestaWithUserAndVotosDTO.setRespuestaText(respuesta.getRespuestaText());
                        respuestaWithUserAndVotosDTO.setTotalVotosNegativos(respuesta.getTotalVotosNegativos());
                        respuestaWithUserAndVotosDTO.setTotalVotosPositivos(respuesta.getTotalVotosPositivos());
                        respuestaWithUserAndVotosDTO.setCreatedAt(respuesta.getCreatedAt());
                        respuestaWithUserAndVotosDTO.setPinned(respuesta.getIsPinned());

                        UserDTO userDTO = new UserDTO();
                        userDTO.setId(respuesta.getUserId());
                        userDTO.setUsername(respuesta.getUsername());
                        userDTO.setAvatar(respuesta.getAvatar());

                        respuestaWithUserAndVotosDTO.setUser(userDTO);

                        respuestaWithUserAndVotosDTOList.add(respuestaWithUserAndVotosDTO);
                    });
                return respuestaWithUserAndVotosDTOList;
        }
//-----------
//        List<RespuestaWithUserAndVotosDTO> wow = new ArrayList<>();
//        List<Lolz> respis = respuestaRepo.lolz(1L);
////        return respis;
//        respis.forEach(respi -> {
//            RespuestaWithUserAndVotosDTO respuestaWithUserAndVotosDTO = new RespuestaWithUserAndVotosDTO();
//            respuestaWithUserAndVotosDTO.setId(respi.getId());
//            respuestaWithUserAndVotosDTO.setRespuestaText(respi.getRespuestaText());
//            respuestaWithUserAndVotosDTO.setCreatedAt(respi.getCreatedAt());
//            respuestaWithUserAndVotosDTO.setTotalVotosPositivos(respi.getTotalVotosPositivos());
//            wow.add(respuestaWithUserAndVotosDTO);
//        });
//return wow;
//----------
//        if(sort != null && sort.equals("totalVotosPositivos")) {
//            respuestas = respuestaRepo.findByPregunta_IdOrderByIsPinnedDesc(preguntaId, Sort.by(Sort.Direction.DESC, sort));
//        } else {
//            respuestas = respuestaRepo.findByPregunta_IdOrderByIsPinnedDesc(preguntaId);
//        }


//        List<RespuestaWithUserAndVotosDTO> respuestaWithUserAndVotosDTOList = new ArrayList<>();
//
//        respuestas.forEach(respuesta -> {
//            RespuestaWithUserAndVotosDTO respuestaWithUserAndVotosDTO = respuestaDTOConverter(respuesta);
//            respuestaWithUserAndVotosDTOList.add(respuestaWithUserAndVotosDTO);
//        });
//
//
//        return respuestaWithUserAndVotosDTOList;


    }

    @Override
    public RespuestaWithUserAndVotosDTO findOne(Long id) {
//        Optional<Respuesta> respuestaOpt = respuestaRepo.findById(id);
//        if (respuestaOpt.isPresent()) {
//            Respuesta respuesta = respuestaOpt.get();
//            return respuestaDTOConverter(respuesta);
//        }

        RespuestaDTO respuesta = respuestaRepo.findRespuestasUserVotosById(id);

            RespuestaWithUserAndVotosDTO respuestaWithUserAndVotosDTO = new RespuestaWithUserAndVotosDTO();

            respuestaWithUserAndVotosDTO.setId(respuesta.getId());
            respuestaWithUserAndVotosDTO.setRespuestaText(respuesta.getRespuestaText());
            respuestaWithUserAndVotosDTO.setTotalVotosNegativos(respuesta.getTotalVotosNegativos());
            respuestaWithUserAndVotosDTO.setTotalVotosPositivos(respuesta.getTotalVotosPositivos());
            respuestaWithUserAndVotosDTO.setCreatedAt(respuesta.getCreatedAt());
            respuestaWithUserAndVotosDTO.setPinned(respuesta.getIsPinned());

            UserDTO userDTO = new UserDTO();
            userDTO.setId(respuesta.getUserId());
            userDTO.setUsername(respuesta.getUsername());
            userDTO.setAvatar(respuesta.getAvatar());

            respuestaWithUserAndVotosDTO.setUser(userDTO);


        return respuestaWithUserAndVotosDTO;
    }

    @Override
    public Respuesta addOne(Long preguntaId, Respuesta respuesta) {
//        respuesta.setUser(); get from token i guess

        Pregunta pregunta = preguntaService.findOne(preguntaId); //throws error if null?
        respuesta.setPregunta(pregunta);
        respuesta.setCreatedAt(LocalDateTime.now());
        Respuesta newRespuesta = respuestaRepo.save(respuesta);
        return newRespuesta;
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
