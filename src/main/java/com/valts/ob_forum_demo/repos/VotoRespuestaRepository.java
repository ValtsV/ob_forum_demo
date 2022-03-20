package com.valts.ob_forum_demo.repos;

import com.valts.ob_forum_demo.models.VotoRespuesta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotoRespuestaRepository extends JpaRepository<VotoRespuesta, Long> {
}
