package com.valts.ob_forum_demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
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
    @Column(length = 4000)
    private String description;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "is_pinned")
    private boolean isPinned;


    @ManyToOne()
    @JsonIgnore
    private Tema tema;

    @ManyToOne()
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "pregunta", orphanRemoval = true)
    @JsonIgnore
    private List<Respuesta> respuestas;

    @ManyToMany(mappedBy = "followedPreguntas")
    @JsonIgnore
    private List<User> followers;

    @OneToMany(mappedBy = "pregunta", orphanRemoval = true)
    @JsonIgnore
    private List<VotoPregunta> votosPregunta;

    public Pregunta(Long id, String title, String description, LocalDateTime createdAt, boolean isPinned) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.isPinned = isPinned;
    }
}
