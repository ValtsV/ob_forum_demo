package com.valts.ob_forum_demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)


public abstract class Voto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean voto; //true for +1, false for -1, if user clicks again - delete the record

    @ManyToOne()
    @JsonIgnore
    private User user;


    public Voto(Long id, boolean voto) {
        this.id = id;
        this.voto = voto;
    }
}
