package com.valts.ob_forum_demo.repos;

import com.valts.ob_forum_demo.dto.Lolz;
import com.valts.ob_forum_demo.dto.RespuestaDTO;
import com.valts.ob_forum_demo.dto.RespuestaWithUserAndVotosDTO;
import com.valts.ob_forum_demo.models.Respuesta;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RespuestaRepository extends JpaRepository<Respuesta, Long> {
    List<Respuesta> findByPregunta_Id(Long id);

    List<Respuesta> findByPregunta_IdOrderByCreatedAtDesc(Long id);

    List<Respuesta> findByPregunta_IdOrderByIsPinnedDesc(Long id);

    List<Respuesta> findByPregunta_IdOrderByIsPinnedDesc(Long id, Sort s);

    @Query(
        value = "SELECT r.id as id, r.respuesta_text as respuestaText, r.created_at as createdAt,r.is_pinned as isPinned, r.user_id as userId, count(case when voto = true then v.respuesta_id end) AS totalVotosPositivos, count(case when voto = false then v.respuesta_id end) as totalVotosNegativos, u.username, u.avatar\n" +
        "FROM respuestas r LEFT JOIN voto v ON (r.id=v.respuesta_id)   LEFT join users u on (r.user_id = u.id) WHERE r.pregunta_id = 1\n" +
        "GROUP BY r.id, u.id ORDER BY r.is_pinned DESC;",
            nativeQuery = true
    )
    List<RespuestaDTO> findRespuestasUserVotosByPreguntaId(Long id);
//    List<Lolz> lolz(Long id);

    @Query(
            value = "SELECT r.id as id, r.respuesta_text as respuestaText, r.created_at as createdAt,r.is_pinned as isPinned, r.user_id as userId, count(case when voto = true then v.respuesta_id end) AS totalVotosPositivos, count(case when voto = false then v.respuesta_id end) as totalVotosNegativos, u.username as username, u.avatar as avatar\n" +
                    "FROM respuestas r LEFT JOIN voto v ON (r.id=v.respuesta_id)   LEFT join users u on (r.user_id = u.id) WHERE r.pregunta_id = ?1\n" +
                    "GROUP BY r.id, u.id ORDER BY totalVotosPositivos DESC;",
            nativeQuery = true
    )
    List<RespuestaDTO> findRespuestasUserVotosByPreguntaIdOrderedByPosVotes(Long id);

    @Query(
            value = "SELECT r.id as id, r.respuesta_text as respuestaText, r.created_at as createdAt,r.is_pinned as isPinned, r.user_id as userId, count(case when voto = true then v.respuesta_id end) AS totalVotosPositivos, count(case when voto = false then v.respuesta_id end) as totalVotosNegativos, u.username as username, u.avatar as avatar\n" +
                    "FROM respuestas r LEFT JOIN voto v ON (r.id=v.respuesta_id)   LEFT join users u on (r.user_id = u.id) WHERE r.pregunta_id = ?1\n" +
                    "GROUP BY r.id, u.id ORDER BY r.created_at DESC;",
            nativeQuery = true
    )
    List<RespuestaDTO> findRespuestasUserVotosByPreguntaIdOrderedByCreatedAtDesc(Long id);

    @Query(
            value = "SELECT r.id as id, r.respuesta_text as respuestaText, r.created_at as createdAt,r.is_pinned as isPinned, r.user_id as userId, count(case when voto = true then v.respuesta_id end) AS totalVotosPositivos, count(case when voto = false then v.respuesta_id end) as totalVotosNegativos, u.username as username, u.avatar as avatar\n" +
                    "FROM respuestas r LEFT JOIN voto v ON (r.id=v.respuesta_id)   LEFT join users u on (r.user_id = u.id) WHERE r.pregunta_id = ?1\n" +
                    "GROUP BY r.id, u.id ORDER BY r.created_at ASC;",
            nativeQuery = true
    )
    List<RespuestaDTO> findRespuestasUserVotosByPreguntaIdOrderedByCreatedAtAsc(Long id);

    @Query(
            value = "SELECT r.id as id, r.respuesta_text as respuestaText, r.created_at as createdAt,r.is_pinned as isPinned, r.user_id as userId, count(case when voto = true then v.respuesta_id end) AS totalVotosPositivos, count(case when voto = false then v.respuesta_id end) as totalVotosNegativos, u.username, u.avatar\n" +
                    "FROM respuestas r LEFT JOIN voto v ON (r.id=v.respuesta_id)   LEFT join users u on (r.user_id = u.id) WHERE r.id = ?1\n" +
                    "GROUP BY r.id, u.id;",
            nativeQuery = true
    )
    RespuestaDTO findRespuestasUserVotosById(Long id);
}
