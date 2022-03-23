package com.valts.ob_forum_demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

//Lombok
@Data
@NoArgsConstructor
@AllArgsConstructor


@Entity
@Table(name = "preguntas")
public class Pregunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @Column(name = "created_at")
    private LocalDate createdAt;
    @Column(name = "is_pinned")
    private boolean isPinned;


    @ManyToOne()
    @JsonIgnore
    private Tema tema;

    @ManyToOne()
    private User user;

    @OneToMany(mappedBy = "pregunta")
    private List<Respuesta> respuestas;

    @ManyToMany(mappedBy = "followedPreguntas")
    private List<User> followers;

    public Pregunta(Long id, String title, String description, LocalDate createdAt, boolean isPinned) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.isPinned = isPinned;
    }
}
