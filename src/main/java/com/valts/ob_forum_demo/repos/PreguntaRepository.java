package com.valts.ob_forum_demo.repos;

import com.valts.ob_forum_demo.dto.PreguntaDTOi;
import com.valts.ob_forum_demo.models.Pregunta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PreguntaRepository extends JpaRepository<Pregunta, Long> {

    @Query(
            value = """
                    select
                    count(case when v.voto = true then 1 end) as totalVotosPositivos,
                    count(case when v.voto = false then 1 end) as totalVotosNegativos,
                    p.id as id, p.description as description, p.created_at as createdAt, p.is_pinned as isPinned, p.title as title, p.tema_id as temaId, p.user_id as userId,
                    u.username as userUsername,
                    u.avatar as userAvatar,
                    (select count(case when p.id = r.pregunta_id then 1 end) from respuestas r) as totalRespuestas
                    from voto v
                    right join preguntas p on (v.pregunta_id = p.id)
                    left join users u on (p.user_id = u.id)
                    group by v.pregunta_id, p.id, u.username, u.avatar order by p.is_pinned desc""",
            nativeQuery = true
    )
    List<PreguntaDTOi> findPreguntasUsersVotosOrderedByIsPinned();

    @Query(
            value = """
                    select
                    count(case when v.voto = true then 1 end) as totalVotosPositivos,
                    count(case when v.voto = false then 1 end) as totalVotosNegativos,
                    p.id as id, p.description as description, p.created_at as createdAt, p.is_pinned as isPinned, p.title as title, p.tema_id as temaId, p.user_id as userId,
                    u.username as userUsername,
                    u.avatar as userAvatar,
                    (select count(case when p.id = r.pregunta_id then 1 end) from respuestas r) as totalRespuestas
                    from voto v
                    right join preguntas p on (v.pregunta_id = p.id)
                    left join users u on (p.user_id = u.id)
                    where p.tema_id = ?1
                    group by v.pregunta_id, p.id, u.username, u.avatar""",
            nativeQuery = true
    )
    List<PreguntaDTOi> findPreguntasUsersVotosByTemaIdOrderedByIsPinned(Long id);



    @Query(
            value = """
                    select\s
                    count(case when v.voto = true then 1 end) as totalVotosPositivos,\s
                    count(case when v.voto = false then 1 end) as totalVotosNegativos,\s
                    p.id as id,
                    p.created_at as createdAt,
                    p.description as description,
                    p.is_pinned as isPinned,
                    p.title as title,
                    p.tema_id as temaId,
                    p.user_id as userId,
                    (select count(case when p.id = r.pregunta_id then 1 end) from respuestas r) as totalRespuestas,
                    u.username as userUsername,
                    u.avatar as userAvatar
                    from voto v
                    right join preguntas p on (v.pregunta_id = p.id)
                    left join users u on (p.user_id = u.id)
                    where pregunta_id = ?1 group by v.pregunta_id, p.id, u.username, u.avatar""",
            nativeQuery = true
    )
    Optional<PreguntaDTOi> findPreguntaUserTotalVotosByPreguntaId(Long id);


}
