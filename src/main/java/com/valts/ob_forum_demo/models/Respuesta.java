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
    private String respuestaText;
    private Date createdAt;
    private boolean isPinned;

    @ManyToOne(optional = false)
    @JoinColumn(name = "pregunta_id", referencedColumnName = "id", nullable = false)
    private Pregunta pregunta;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "respuesta")
    private List<VotoRespuesta> votosRespuesta;



}
