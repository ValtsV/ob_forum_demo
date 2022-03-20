package com.valts.ob_forum_demo.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

//Lombok
@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
@Table(name = "notificaciones")
public class Notificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    private Date timestamp;

    @ManyToOne()
    private User user;

}
