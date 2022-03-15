package com.valts.ob_forum_demo.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

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
    private Enum role; //??

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Pregunta> preguntas;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Respuesta> respuestas;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Notificacion> notificacion;

    @ManyToMany(mappedBy = "followers")
    private List<Pregunta> followedPreguntas;

    @ManyToMany(mappedBy = "followers")
    private List<Tema> followedTemas;

    @OneToMany(mappedBy = "user")
    private List<VotoPregunta> votosPregunta;

    @OneToMany(mappedBy = "user")
    private List<VotoRespuesta> votosRespuesta;
}
