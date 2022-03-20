package com.valts.ob_forum_demo.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class VotoPreguntaKey implements Serializable {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "pregunta_id")
    private Long preguntaId;

}
