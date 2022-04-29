package com.valts.ob_forum_demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//Lombok
@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "modulos")
public class Modulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String title;

    @OneToMany(mappedBy = "modulo")
    private List<Tema> temas = new ArrayList<>();

    public Modulo(Long id, String title) {
        this.id = id;
    }
}
