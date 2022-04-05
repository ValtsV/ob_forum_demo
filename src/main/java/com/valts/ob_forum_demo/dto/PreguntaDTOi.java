package com.valts.ob_forum_demo.dto;

import java.time.LocalDateTime;

public interface PreguntaDTOi {
//    pregunta
    Long getId();
    String getTitle();
    String getDescription();
    LocalDateTime getCreatedAt();
    boolean getIsPinned();
    Long getTemaId();
    Long getTotalVotosPositivos();
    Long getTotalVotosNegativos();
    Integer getTotalRespuestas();
    //    user
    Long getUserId();
    String getUserAvatar();
    String getUserUsername();
}
