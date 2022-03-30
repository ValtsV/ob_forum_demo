package com.valts.ob_forum_demo.servicios;

import com.valts.ob_forum_demo.dto.VotoDTO;
import com.valts.ob_forum_demo.models.User;
import com.valts.ob_forum_demo.models.Voto;
import com.valts.ob_forum_demo.models.VotoPregunta;
import com.valts.ob_forum_demo.models.VotoRespuesta;

import java.util.HashMap;
import java.util.List;

public interface VotoService {
    Voto addVoto(Voto voto);

    List<VotoPregunta> findAllByPreguntaId(Long id);

    List<VotoRespuesta> findAllByRespuestaId(Long id);

    HashMap<String, Integer> getVotosByRespuestaId(Long respuestaId);

    Voto findOne(Long id);

    Voto addOne(VotoDTO votoDTO);

    Voto updateOne(Long id, Voto voto);

    void deleteOne(Long id);

    void deleteAll();
}
