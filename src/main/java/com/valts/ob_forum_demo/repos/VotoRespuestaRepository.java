package com.valts.ob_forum_demo.repos;

import com.valts.ob_forum_demo.models.Voto;
import com.valts.ob_forum_demo.models.VotoPregunta;
import com.valts.ob_forum_demo.models.VotoRespuesta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface VotoRespuestaRepository extends VotoBaseRepository<VotoRespuesta> {

    List<VotoRespuesta> findAllByRespuesta_Id(Long id);

    Integer countByVoto(boolean b);

    Integer countByVotoAndRespuestaId(boolean b, Long id);
}
