package com.valts.ob_forum_demo.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class VotoRespuestaKey implements Serializable {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "respuesta_id")
    private Long respuestaId;
}
