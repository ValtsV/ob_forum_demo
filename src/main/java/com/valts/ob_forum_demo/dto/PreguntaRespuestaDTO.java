package com.valts.ob_forum_demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PreguntaRespuestaDTO {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime createdAt;
    private boolean isPinned;
    private Long temaId;
    private Integer totalRespuestas;
    private Long totalVotosPositivos;
    private Long totalVotosNegativos;
    private List<RespuestaDTO> respuestas;
}
