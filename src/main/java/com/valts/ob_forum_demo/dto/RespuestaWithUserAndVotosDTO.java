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
public class RespuestaWithUserAndVotosDTO {
    private Long id;
    private String respuestaText;
    private LocalDateTime createdAt;
    private boolean isPinned;
    private Long totalVotosPositivos;
    private Long totalVotosNegativos;
    private UserDTO user;


}


