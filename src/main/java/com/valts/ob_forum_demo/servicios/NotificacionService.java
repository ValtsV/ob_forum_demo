package com.valts.ob_forum_demo.servicios;

import com.valts.ob_forum_demo.models.Notificacion;

import java.util.List;

public interface NotificacionService {
    List<Notificacion> findAll();

    Notificacion findOne(Long id);

    Notificacion addOne(Notificacion notificacion);

    Notificacion updateOne(Long id, Notificacion notificacion);

    void deleteOne(Long id);

    void deleteAll();
}
