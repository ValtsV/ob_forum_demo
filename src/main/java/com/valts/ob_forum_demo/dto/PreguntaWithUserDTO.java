package com.valts.ob_forum_demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PreguntaWithUserDTO {

    private Long id;
    private String title;
    private String description;
    private LocalDateTime createdAt;
    private boolean isPinned;
    private Integer totalRespuestas;
    private UserDTO user;
}
