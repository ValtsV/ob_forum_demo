package com.valts.ob_forum_demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Embeddable

public class VotoPreguntaKey implements Serializable {

//    @Column(name = "user_id")
//    private Long userId;
    private String userId;

//    @Column(name = "pregunta_id")
//    private Long preguntaId;
    private String preguntaId;

}
