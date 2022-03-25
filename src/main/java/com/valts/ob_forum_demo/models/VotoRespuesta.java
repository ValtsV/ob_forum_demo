package com.valts.ob_forum_demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean voto; //true for +1, false for -1, if user clicks again - delete the record

    @ManyToOne
    @JoinColumn(name = "respuesta_id")
    private Respuesta respuesta;

    @ManyToOne()
    @JsonIgnore
    private User user;

    public VotoRespuesta(Long id, boolean voto) {
        this.id = id;
        this.voto = voto;
    }
}
