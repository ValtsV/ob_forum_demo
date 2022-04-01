package com.valts.ob_forum_demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//Lombok
@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
public class VotoRespuesta extends Voto {

    @ManyToOne
    @JoinColumn(name = "respuesta_id")
    @JsonIgnore
    private Respuesta respuesta;

    public VotoRespuesta(Long id, boolean voto) {
        super(id, voto);
    }


}
