package com.valts.ob_forum_demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
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
    private LocalDateTime createdAt;
    @Column(name = "is_pinned")
    private boolean isPinned;

    @ManyToOne()
    @JsonIgnore
    private Pregunta pregunta;

    @ManyToOne()
    private User user;

    @OneToMany(mappedBy = "respuesta", orphanRemoval = true)
    @JsonIgnore
    private List<VotoRespuesta> votosRespuesta;

    public Respuesta(Long id, String respuestaText, LocalDateTime createdAt, boolean isPinned) {
        this.id = id;
        this.respuestaText = respuestaText;
        this.createdAt = createdAt;
        this.isPinned = isPinned;
    }
}
