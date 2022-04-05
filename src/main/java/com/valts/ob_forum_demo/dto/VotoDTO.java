package com.valts.ob_forum_demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VotoDTO {
    private Long id;
    private boolean voto;
    private Long userId;
    private Long preguntaId;
    private Long respuestaId;
}
