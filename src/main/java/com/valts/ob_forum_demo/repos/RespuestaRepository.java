package com.valts.ob_forum_demo.repos;

import com.valts.ob_forum_demo.dto.RespuestaDTOi;
import com.valts.ob_forum_demo.models.Respuesta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RespuestaRepository extends JpaRepository<Respuesta, Long> {

    @Query(
        value = """
                SELECT r.id as id, r.respuesta_text as respuestaText, r.created_at as createdAt,r.is_pinned as isPinned, r.user_id as userId, count(case when voto = true then v.respuesta_id end) AS totalVotosPositivos, count(case when voto = false then v.respuesta_id end) as totalVotosNegativos, u.username, u.avatar
                FROM respuestas r LEFT JOIN voto v ON (r.id=v.respuesta_id)   LEFT join users u on (r.user_id = u.id) WHERE r.pregunta_id = 1
                GROUP BY r.id, u.id ORDER BY r.is_pinned DESC;""",
            nativeQuery = true
    )
    List<RespuestaDTOi> findRespuestasUserVotosByPreguntaId(Long id);

    @Query(
            value = """
                    SELECT r.id as id, r.respuesta_text as respuestaText, r.created_at as createdAt,r.is_pinned as isPinned, r.user_id as userId, count(case when voto = true then v.respuesta_id end) AS totalVotosPositivos, count(case when voto = false then v.respuesta_id end) as totalVotosNegativos, u.username as username, u.avatar as avatar
                    FROM respuestas r LEFT JOIN voto v ON (r.id=v.respuesta_id)   LEFT join users u on (r.user_id = u.id) WHERE r.pregunta_id = ?1
                    GROUP BY r.id, u.id ORDER BY totalVotosPositivos DESC;""",
            nativeQuery = true
    )
    List<RespuestaDTOi> findRespuestasUserVotosByPreguntaIdOrderedByPosVotes(Long id);

    @Query(
            value = """
                    SELECT r.id as id, r.respuesta_text as respuestaText, r.created_at as createdAt,r.is_pinned as isPinned, r.user_id as userId, count(case when voto = true then v.respuesta_id end) AS totalVotosPositivos, count(case when voto = false then v.respuesta_id end) as totalVotosNegativos, u.username as username, u.avatar as avatar
                    FROM respuestas r LEFT JOIN voto v ON (r.id=v.respuesta_id)   LEFT join users u on (r.user_id = u.id) WHERE r.pregunta_id = ?1
                    GROUP BY r.id, u.id ORDER BY r.created_at DESC;""",
            nativeQuery = true
    )
    List<RespuestaDTOi> findRespuestasUserVotosByPreguntaIdOrderedByCreatedAtDesc(Long id);

    @Query(
            value = """
                    SELECT r.id as id, r.respuesta_text as respuestaText, r.created_at as createdAt,r.is_pinned as isPinned, r.user_id as userId, count(case when voto = true then v.respuesta_id end) AS totalVotosPositivos, count(case when voto = false then v.respuesta_id end) as totalVotosNegativos, u.username as username, u.avatar as avatar
                    FROM respuestas r LEFT JOIN voto v ON (r.id=v.respuesta_id)   LEFT join users u on (r.user_id = u.id) WHERE r.pregunta_id = ?1
                    GROUP BY r.id, u.id ORDER BY r.created_at ASC;""",
            nativeQuery = true
    )
    List<RespuestaDTOi> findRespuestasUserVotosByPreguntaIdOrderedByCreatedAtAsc(Long id);

    @Query(
            value = """
                    SELECT r.id as id, r.respuesta_text as respuestaText, r.created_at as createdAt,r.is_pinned as isPinned, r.user_id as userId, count(case when voto = true then v.respuesta_id end) AS totalVotosPositivos, count(case when voto = false then v.respuesta_id end) as totalVotosNegativos, u.username, u.avatar
                    FROM respuestas r LEFT JOIN voto v ON (r.id=v.respuesta_id)   LEFT join users u on (r.user_id = u.id) WHERE r.id = ?1
                    GROUP BY r.id, u.id;""",
            nativeQuery = true
    )
    RespuestaDTOi findRespuestasUserVotosById(Long id);
}
