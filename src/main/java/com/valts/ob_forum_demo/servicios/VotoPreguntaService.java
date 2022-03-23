package com.valts.ob_forum_demo.servicios;

import com.valts.ob_forum_demo.models.Respuesta;
import com.valts.ob_forum_demo.models.VotoPregunta;

import java.util.List;

public interface VotoPreguntaService {
    List<VotoPregunta> findAll();

    VotoPregunta findOne(Long id);

    VotoPregunta addOne(VotoPregunta votoPregunta);

    VotoPregunta updateOne(Long id, VotoPregunta votoPregunta);

    void deleteOne(Long id);

    void deleteAll();
}
