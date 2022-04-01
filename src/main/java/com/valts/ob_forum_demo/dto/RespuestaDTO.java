package com.valts.ob_forum_demo.dto;

import java.time.LocalDateTime;

public interface RespuestaDTO {
    Long getId();
    String getRespuestaText();
    LocalDateTime getCreatedAt();
    boolean getIsPinned();
    Long getTotalVotosPositivos();
    Long getTotalVotosNegativos();
    Long getPreguntaId();
    Long getUserId();
    String getUsername();
    String getAvatar();

}
