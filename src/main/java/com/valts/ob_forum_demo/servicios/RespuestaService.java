package com.valts.ob_forum_demo.servicios;

import com.valts.ob_forum_demo.dto.RespuestaWithUserAndVotosDTO;
import com.valts.ob_forum_demo.models.Curso;
import com.valts.ob_forum_demo.models.Respuesta;

import java.util.List;

public interface RespuestaService {
    List<RespuestaWithUserAndVotosDTO> findAll(Long preguntaId, String s, String o);

    RespuestaWithUserAndVotosDTO findOne(Long id);

    Respuesta addOne(Long id, Respuesta respuesta);

    Respuesta updateOne(Long id, Respuesta respuesta);

    void deleteById(Long id);

    void deleteAll();


}
