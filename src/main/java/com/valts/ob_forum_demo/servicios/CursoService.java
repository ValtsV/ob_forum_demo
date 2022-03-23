package com.valts.ob_forum_demo.servicios;


import com.valts.ob_forum_demo.models.Curso;

import java.util.List;

public interface CursoService {
    List<Curso> findAll();

    Curso findOne(Long id);

    Curso addCurso(Curso curso);

    Curso updateCurso(Long id, Curso curso);

    void deleteCurso(Long id);

    void deleteAllCursos();
}
