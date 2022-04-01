package com.valts.ob_forum_demo.models;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//Lombok
@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
public class VotoPregunta extends Voto {

    @ManyToOne
    @JoinColumn(name = "pregunta_id")
    @JsonIgnore
    private Pregunta pregunta;

    public VotoPregunta(Long id, boolean voto) {
        super(id, voto);
    }

}
