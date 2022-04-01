package com.valts.ob_forum_demo.servicios;

import com.valts.ob_forum_demo.dto.VotoDTO;
import com.valts.ob_forum_demo.models.Voto;
import com.valts.ob_forum_demo.models.VotoPregunta;
import com.valts.ob_forum_demo.models.VotoRespuesta;

import java.util.List;

public interface VotoService {

    List<VotoPregunta> findAllByPreguntaId(Long id);

    List<VotoRespuesta> findAllByRespuestaId(Long id);

    Voto findOne(Long id);


    Voto updateOne(Long id);

    void deleteOne(Long id);

    void deleteAll();

    Voto addVotoRespuesta(Long id, VotoDTO voto);

    Voto addVotoPregunta(Long id, VotoDTO voto);
}
