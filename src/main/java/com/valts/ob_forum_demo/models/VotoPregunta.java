package com.valts.ob_forum_demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//Lombok
@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "votos_preguntas")
public class VotoPregunta {

    @EmbeddedId
    private VotoPreguntaKey id;
    private boolean voto; //true for +1, false for -1, if user clicks again - delete the record

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("preguntaId")
    @JoinColumn(name = "pregunta_id")
    private Pregunta pregunta;


}
