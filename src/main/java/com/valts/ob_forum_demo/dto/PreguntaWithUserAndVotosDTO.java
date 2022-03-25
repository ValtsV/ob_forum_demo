package com.valts.ob_forum_demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PreguntaWithUserAndVotosDTO {
    private Long id;
    private String title;
    private String description;
    private LocalDate createdAt;
    private boolean isPinned;
    private Integer totalRespuestas;
    private Integer totalVotosPositivos;
    private Integer totalVotosNegativos;
    private UserDTO user;
}
