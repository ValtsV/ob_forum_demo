package com.valts.ob_forum_demo.servicios.implementations;

import com.valts.ob_forum_demo.dto.PreguntaDTO;
import com.valts.ob_forum_demo.dto.TemaDTO;
import com.valts.ob_forum_demo.models.Pregunta;
import com.valts.ob_forum_demo.models.Tema;
import com.valts.ob_forum_demo.repos.TemaRepository;
import com.valts.ob_forum_demo.servicios.TemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TemaServiceImpl implements TemaService {

    @Autowired
    TemaRepository temaRepo;

    @Override
    public List<Tema> findAll(String email) {
//        TODO: filter by attended cursos by email
        return temaRepo.findAllByOrderByIsPinnedDesc();
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
        return temaRepo.save(tema);
    }

    @Override
    public Tema updateTema(Long id, Tema tema) {
        Optional<Tema> temaOptional = temaRepo.findById(id);
        if (temaOptional.isPresent()) {
            Tema newTema = temaOptional.get();

            newTema.setTitle(tema.getTitle());
            newTema.setDescription(tema.getDescription());

            return temaRepo.save(newTema);

        }
        return null;
    }

    @Override
    public void deleteTema(Long id) {
        Optional<Tema> temaOptional = temaRepo.findById(id);
        if (temaOptional.isPresent()) {
            temaRepo.deleteById(id);
        }
    }

    @Override
    public void deleteAllTemas() {
        temaRepo.deleteAll();
    }

    @Override
    public TemaDTO getTemaById(Long id) {
        Optional<Tema> temaOptional =  temaRepo.findById(id);
        if (temaOptional.isPresent()) {
            return convertTemaDTO(temaOptional.get());
        }
        return null;
    }


    private TemaDTO convertTemaDTO(Tema tema) {
        TemaDTO temaDto = new TemaDTO();
        temaDto.setId(tema.getId());
        temaDto.setTitle(tema.getTitle());
        temaDto.setDescription(tema.getDescription());
        temaDto.setPinned(tema.isPinned());

//        temadto set listofpreguntaDtos
        List<Pregunta> preguntas = tema.getPreguntas();
        List<PreguntaDTO> preguntasDto = preguntas.stream().map(this::convertPreguntaDTO).collect(Collectors.toList());


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
    public List<Tema> getTemasFilteredBy(Long cursoId, List<Long> moduloId) {
        if (moduloId == null) {
            return temaRepo.findByCursoId(cursoId);
        }

        return temaRepo.findByCursoIdAndModuloIdIn(cursoId, moduloId);

    }
}
