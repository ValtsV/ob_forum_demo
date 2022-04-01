package com.valts.ob_forum_demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;

//Lombok
@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;
    private String avatar;

    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Pregunta> preguntas;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<VotoPregunta> votosPregunta;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Respuesta> respuestas;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<VotoRespuesta> votosRespuesta;

    @ManyToMany()
    @JoinTable(
            name = "preguntas_followers",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "pregunta_id")
    )
    @JsonIgnore
    private List<Pregunta> followedPreguntas;

        @ManyToMany()
        @JoinTable(
                name = "temas_followers",
                joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "tema_id")
        )
        @JsonIgnore
        private List<Tema> followedTemas;

        @ManyToMany(fetch = FetchType.EAGER)
        @JoinTable(
                name = "cursos_users",
                joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "curso_id")
        )
        @JsonIgnore
        private List<Curso> attendedCursos;

    public User(Long id, String username, String password, String email, String avatar) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.avatar = avatar;
    }
}
