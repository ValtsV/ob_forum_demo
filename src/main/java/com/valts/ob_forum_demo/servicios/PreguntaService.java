package com.valts.ob_forum_demo.servicios;

import com.valts.ob_forum_demo.dto.PreguntaAndRespuestaUserVotosDTO;
import com.valts.ob_forum_demo.dto.PreguntaWithUserAndVotosDTO;
import com.valts.ob_forum_demo.dto.PreguntaWithUserDTO;
import com.valts.ob_forum_demo.models.Curso;
import com.valts.ob_forum_demo.models.Pregunta;

import java.util.List;


public interface PreguntaService {
    List<Pregunta> findAll();

    Pregunta findOne(Long id);

    Pregunta addOne(Pregunta pregunta, Long temaId);

    Pregunta updateOne(Long id, Pregunta pregunta);

    void deleteById(Long id);

    void deleteAll();

    List<PreguntaWithUserDTO> getPreguntas();

    PreguntaAndRespuestaUserVotosDTO getPreguntasById(Long id);

    List<PreguntaWithUserDTO> getPreguntasByTemaId(Long id);
}
