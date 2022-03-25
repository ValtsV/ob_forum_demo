package com.valts.ob_forum_demo.servicios.implementations;

import com.valts.ob_forum_demo.dto.PreguntaDTO;
import com.valts.ob_forum_demo.dto.TemaDTO;
import com.valts.ob_forum_demo.models.Modulo;
import com.valts.ob_forum_demo.models.Pregunta;
import com.valts.ob_forum_demo.models.Tema;
import com.valts.ob_forum_demo.repos.TemaRepository;
import com.valts.ob_forum_demo.servicios.TemaService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TemaServiceImpl implements TemaService {

    TemaRepository temaRepo;

    public TemaServiceImpl(TemaRepository temaRepo) {
        this.temaRepo = temaRepo;
    }

    @Override
    public List<Tema> findAll() {
        return temaRepo.findAll();
    }

    @Override
    public Tema findOne(Long id) {
        Optional<Tema> temaOpt = temaRepo.findById(id);
        if (temaOpt.isPresent()) {
            return temaOpt.get();
        }

        return null;
    }

    @Override
    public Tema addTema(Tema tema) {
        Tema savedTema = temaRepo.save(tema);
        return savedTema;
    }

    @Override
    public Tema updateTema(Long id, Tema tema) {
        Optional<Tema> temaOpt = temaRepo.findById(id);
        if (temaOpt.isPresent()) {
            tema.setId(id);
            return temaRepo.save(tema);

        }
        return null;
    }

    @Override
    public void deleteTema(Long id) {
        Optional<Tema> temaOpt = temaRepo.findById(id);
        if (temaOpt.isPresent()) {
            temaRepo.deleteById(id);
        }
    }

    @Override
    public void deleteAllTemas() {
        temaRepo.deleteAll();
    }

    @Override
    public TemaDTO getTemaDto(Long id) {
        Optional<Tema> temaOpt =  temaRepo.findById(id);
        if (temaOpt.isPresent()) {
            TemaDTO temaDto = convertTemaDTO(temaOpt.get());
            return temaDto;
        }
        return null;
    }


    public TemaDTO convertTemaDTO(Tema tema) {
        TemaDTO temaDto = new TemaDTO();
        temaDto.setId(tema.getId());
        temaDto.setTitle(tema.getTitle());
        temaDto.setDescription(tema.getDescription());
        temaDto.setPinned(tema.isPinned());

//        temadto set listofpreguntaDtos
        List<Pregunta> preguntas = tema.getPreguntas();
        List<PreguntaDTO> preguntasDto = preguntas.stream().map(pregunta -> convertPreguntaDTO(pregunta)).collect(Collectors.toList());


        temaDto.setPreguntas(preguntasDto);
        return temaDto;
    }

    public PreguntaDTO convertPreguntaDTO(Pregunta pregunta) {
        PreguntaDTO preguntaDto = new PreguntaDTO();
        preguntaDto.setId(pregunta.getId());
        preguntaDto.setTitle(pregunta.getTitle());
        preguntaDto.setDescription(pregunta.getDescription());
        preguntaDto.setCreatedAt(pregunta.getCreatedAt());
        preguntaDto.setPinned(pregunta.isPinned());
        preguntaDto.setTotalRespuestas(pregunta.getRespuestas().size());

        return preguntaDto;
    }

    @Override
    public List<Tema> getTemas2(Long cursoId, List<Long> moduloId) {
        if (moduloId == null) {
            return temaRepo.findByCursoId(cursoId);
        }

        return temaRepo.findByCursoIdAndModuloIdIn(cursoId, moduloId);

    }
}
