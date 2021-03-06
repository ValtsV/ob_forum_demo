package com.valts.ob_forum_demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

//Lombok
@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "temas")
public class Tema implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @Column(name = "is_pinned")
    private boolean isPinned;

    @ManyToOne()
    @JoinColumn(name = "curso_id")
    @JsonIgnore
    private Curso curso;

    @ManyToMany(mappedBy = "followedTemas")
    @JsonIgnore
    private List<User> followers;

    @OneToMany(mappedBy = "tema", orphanRemoval = true)
    private List<Pregunta> preguntas;

    @ManyToOne
    @JsonIgnore
    private Modulo modulo;


    public Tema(Long id, String title, String description, boolean isPinned) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.isPinned = isPinned;
    }
}
