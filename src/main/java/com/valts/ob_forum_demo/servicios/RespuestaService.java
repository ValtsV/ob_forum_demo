package com.valts.ob_forum_demo.servicios;

import com.valts.ob_forum_demo.dto.RespuestaDTO;
import com.valts.ob_forum_demo.models.Respuesta;

import java.util.List;

public interface RespuestaService {
    List<RespuestaDTO> findAll(Long preguntaId, String s, String o);

    RespuestaDTO findOne(Long id);

    Respuesta addOne(Long id, Respuesta respuesta);

    Respuesta updateOne(Long id, Respuesta respuesta);

    void deleteById(Long id);

    void deleteAll();


}
