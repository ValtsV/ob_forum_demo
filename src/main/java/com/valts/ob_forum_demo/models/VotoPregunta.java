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
@Table(name = "votos_pregunta")
public class VotoPregunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean voto; //true for +1, false for -1, if user clicks again - delete the record

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "pregunta_id", referencedColumnName = "id")
    private Pregunta pregunta;
}
