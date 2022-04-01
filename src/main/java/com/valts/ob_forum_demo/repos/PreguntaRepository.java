package com.valts.ob_forum_demo.repos;

import com.valts.ob_forum_demo.models.Pregunta;
import com.valts.ob_forum_demo.models.Tema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PreguntaRepository extends JpaRepository<Pregunta, Long> {
    List<Pregunta> findAllByTemaIdOrderByIsPinnedDesc(Long id);

    List<Pregunta> findAllByOrderByIsPinnedDesc();

}
