package com.valts.ob_forum_demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

//Lombok
@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
@Table(name = "cursos")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String avatar;

    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL)
    private List<Curso> cursos;
}
