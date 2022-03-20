package com.valts.ob_forum_demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

//Lombok
@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "votos_respuestas")
public class VotoRespuesta {

    @EmbeddedId
    private VotoRespuestaKey id;
    private boolean voto; //true for +1, false for -1, if user clicks again - delete the record

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("respuestaId")
    @JoinColumn(name = "respuesta_id")
    private Respuesta respuesta;


}
