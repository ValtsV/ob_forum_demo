package com.valts.ob_forum_demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

//Lombok
@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
@Table(name = "respuestas")
public class Respuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "respuesta_text")
    private String respuestaText;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "is_pinned")
    private boolean isPinned;

    @ManyToOne()
    private Pregunta pregunta;

    @ManyToOne()
    private User user;

}
