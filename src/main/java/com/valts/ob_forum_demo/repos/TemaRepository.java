package com.valts.ob_forum_demo.repos;

import com.valts.ob_forum_demo.models.Modulo;
import com.valts.ob_forum_demo.models.Tema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface TemaRepository extends JpaRepository<Tema, Long> {
    List<Tema> findByCursoId(Long id);

    List<Tema> findByCursoIdAndModuloIdIn(Long id, Collection<Long> modulos);

    List<Tema> findAllByOrderByIsPinnedDesc();
}
