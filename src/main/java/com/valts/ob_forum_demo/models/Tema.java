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
@Table(name = "temas")
public class Tema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @Column(name = "is_pinned")
    private boolean isPinned;

    @ManyToOne(optional = false)
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;

    @OneToOne(mappedBy = "curso", cascade = CascadeType.ALL)
    private Modulo modulo;

    @OneToMany(mappedBy = "tema", cascade = CascadeType.ALL)
    private List<Pregunta> preguntas;

    @ManyToMany
    @JoinTable(
            name = "followers_temas",
            joinColumns = @JoinColumn(name = "tema_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id")
    )
    private List<Tema> followers;
}
