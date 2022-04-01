package com.valts.ob_forum_demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PreguntaAndRespuestaUserVotosDTO {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime createdAt;
    private boolean isPinned;
    private Integer totalRespuestas;
    private List<RespuestaWithUserAndVotosDTO> respuestas;
}
