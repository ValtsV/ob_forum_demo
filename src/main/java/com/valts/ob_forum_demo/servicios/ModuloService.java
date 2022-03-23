package com.valts.ob_forum_demo.servicios;

import com.valts.ob_forum_demo.models.Modulo;

import java.util.List;

public interface ModuloService {
    List<Modulo> findAll();

    Modulo findOne(Long id);

    Modulo addModulo(Modulo modulo);

    Modulo updateModulo(Long id, Modulo modulo);

    void deleteModulo(Long id);

    void deleteAllModulos();
}
