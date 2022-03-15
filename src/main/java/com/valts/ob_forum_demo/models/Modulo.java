package com.valts.ob_forum_demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//Lombok
@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "modulos")
public class Modulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String title;
    private String description;

    @OneToOne
    @JoinColumn(name = "tema_id", referencedColumnName = "id")
    private Tema tema;
}