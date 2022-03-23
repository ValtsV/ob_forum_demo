package com.valts.ob_forum_demo.dto;

import com.valts.ob_forum_demo.models.Pregunta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class TemaDTO {
    private Long id;
    private String title;
    private String description;
    private boolean isPinned;
    private List<PreguntaDTO> preguntas;
//    private Integer totalRespuestas;
}
