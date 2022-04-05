package com.valts.ob_forum_demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
