package com.valts.ob_forum_demo.repos;

import com.valts.ob_forum_demo.models.Voto;
import com.valts.ob_forum_demo.models.VotoPregunta;
import com.valts.ob_forum_demo.models.VotoRespuesta;

import java.util.List;

public interface VotoRepository extends VotoBaseRepository<Voto> {
    List<VotoPregunta> findAllByPregunta_Id(Long id);
    List<VotoRespuesta> findAllByRespuesta_Id(Long id);
}
