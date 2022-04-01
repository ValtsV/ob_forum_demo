package com.valts.ob_forum_demo.repos;

import com.valts.ob_forum_demo.models.VotoPregunta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VotoPreguntaRepository extends VotoBaseRepository<VotoPregunta> {

//    PROBS SAFE TO DELETE

//    List<VotoPregunta> findAllByPregunta_Id(Long id);
}
