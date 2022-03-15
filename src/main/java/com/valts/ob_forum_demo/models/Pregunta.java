package com.valts.ob_forum_demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    private String descripción;
    private Date createdAt;
    private boolean isPinned;

    private Long userId;

    @ManyToOne()
    @JoinColumn(name = "tema_id", referencedColumnName = "id", nullable = false)
    private Long temaId;

    @ManyToOne()
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "pregunta")
    private List<Respuesta> respuestas;

    @ManyToMany
    @JoinTable(
            name = "followers_preguntas",
            joinColumns = @JoinColumn(name = "pregunta_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id")
    )
    private List<User> followers;

    @OneToMany(mappedBy = "pregunta")
    private List<VotoPregunta> votosPregunta;
}
