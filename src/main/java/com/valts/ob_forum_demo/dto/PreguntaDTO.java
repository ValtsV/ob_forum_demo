package com.valts.ob_forum_demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PreguntaDTO {

    private Long id;
    private String title;
    private String description;
    private LocalDateTime createdAt;
    private boolean isPinned;
    private Integer totalRespuestas;
}
