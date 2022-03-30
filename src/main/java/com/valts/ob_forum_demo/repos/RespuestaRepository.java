package com.valts.ob_forum_demo.repos;

import com.valts.ob_forum_demo.models.Respuesta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RespuestaRepository extends JpaRepository<Respuesta, Long> {
    List<Respuesta> findByPregunta_Id(Long id);
}
