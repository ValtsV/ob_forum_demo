package com.valts.ob_forum_demo.servicios;

import com.valts.ob_forum_demo.models.Respuesta;
import com.valts.ob_forum_demo.models.Voto;
import com.valts.ob_forum_demo.models.VotoRespuesta;

import java.util.List;

public interface VotoRespuestaService {
    List<VotoRespuesta> findAll();

    VotoRespuesta findOne(Long id);

    VotoRespuesta addOne(VotoRespuesta votoRespuesta);

    VotoRespuesta updateOne(Long id, VotoRespuesta votoRespuesta);

    void deleteOne(Long id);

    void deleteAll();
}
