package com.valts.ob_forum_demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

//Lombok
@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
//@Table(name = "votos_preguntas")
//@JsonIgnoreProperties({"user", "pregunta"})
public class VotoPregunta extends Voto {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private boolean voto; //true for +1, false for -1, if user clicks again - delete the record

    @ManyToOne
    @JoinColumn(name = "pregunta_id")
    @JsonIgnore
    private Pregunta pregunta;

//    @ManyToOne()
//    @JsonIgnore
//    private User user;





    public VotoPregunta(Long id, boolean voto) {
        super(id, voto);
    }

//    @JsonIgnore
//    public Pregunta getPregunta() {
//        return pregunta;
//    }

}
