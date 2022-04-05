package com.valts.ob_forum_demo.servicios;

import com.valts.ob_forum_demo.dto.*;
import com.valts.ob_forum_demo.models.Pregunta;

import java.util.List;


public interface PreguntaService {
    List<PreguntaDTO> findAll();

    List<PreguntaDTO> findAllByTemaId(Long id);

    Pregunta findOne(Long id);

    PreguntaRespuestaDTO findPreguntaRespuestaDTO(Long id);

    Pregunta addOne(Pregunta pregunta, Long temaId);

    Pregunta updateOne(Long id, Pregunta pregunta);

    void deleteById(Long id);

    void deleteAll();


}
