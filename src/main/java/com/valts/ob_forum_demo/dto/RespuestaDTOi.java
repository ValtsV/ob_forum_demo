package com.valts.ob_forum_demo.dto;

import java.time.LocalDateTime;

public interface RespuestaDTOi {
    Long getId();
    String getRespuestaText();
    LocalDateTime getCreatedAt();
    boolean getIsPinned();
    Long getTotalVotosPositivos();
    Long getTotalVotosNegativos();
    Long getUserId();
    String getUsername();
    String getAvatar();

}
