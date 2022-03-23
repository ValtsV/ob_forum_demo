package com.valts.ob_forum_demo.servicios;

import com.valts.ob_forum_demo.dto.TemaDTO;
import com.valts.ob_forum_demo.models.Curso;
import com.valts.ob_forum_demo.models.Tema;

import java.util.List;

public interface TemaService {
    List<Tema> findAll();

    Tema findOne(Long id);

    Tema addTema(Tema tema);

    Tema updateTema(Long id, Tema tema);

    void deleteTema(Long id);

    void deleteAllTemas();

    TemaDTO getTemaDto(Long id);
}
