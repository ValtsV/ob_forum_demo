package com.valts.ob_forum_demo.servicios.implementations;

import com.valts.ob_forum_demo.models.Curso;
import com.valts.ob_forum_demo.models.Notificacion;
import com.valts.ob_forum_demo.repos.CursoRepository;
import com.valts.ob_forum_demo.repos.NotificacionRepository;
import com.valts.ob_forum_demo.repos.TemaRepository;
import com.valts.ob_forum_demo.servicios.NotificacionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificacionServiceImpl implements NotificacionService {

    private NotificacionRepository notificacionRepo;

    public NotificacionServiceImpl(NotificacionRepository notificacionRepo) {
        this.notificacionRepo = notificacionRepo;
    }

    @Override
    public List<Notificacion> findAll() {
        return null;
    }

    @Override
    public Notificacion findOne(Long id) {
        return null;
    }

    @Override
    public Notificacion addOne(Notificacion notificacion) {
        return null;
    }

    @Override
    public Notificacion updateOne(Long id, Notificacion notificacion) {
        return null;
    }

    @Override
    public void deleteOne(Long id) {

    }

    @Override
    public void deleteAll() {

    }
}
