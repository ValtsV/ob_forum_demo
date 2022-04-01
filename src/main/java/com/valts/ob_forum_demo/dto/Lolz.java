package com.valts.ob_forum_demo.dto;

import java.time.LocalDateTime;

public interface Lolz {
        Long getId();
        String getRespuestaText();
        LocalDateTime getCreatedAt();
        boolean isPinned();
        Long getTotalVotosPositivos();
}
