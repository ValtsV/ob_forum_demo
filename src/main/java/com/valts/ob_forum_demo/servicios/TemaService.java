package com.valts.ob_forum_demo.servicios;

import com.valts.ob_forum_demo.dto.TemaDTO;
import com.valts.ob_forum_demo.models.Tema;

import java.util.List;

public interface TemaService {
    List<Tema> findAll(String s);

    Tema findOne(Long id);

    Tema addTema(Tema tema);

    Tema updateTema(Long id, Tema tema);

    void deleteTema(Long id);

    void deleteAllTemas();

    TemaDTO getTemaById(Long id);

    List<Tema> getTemasFilteredBy(Long cursoId, List<Long> moduloId);
}
