package com.valts.ob_forum_demo.servicios.implementations;

import com.valts.ob_forum_demo.models.Curso;
import com.valts.ob_forum_demo.repos.CursoRepository;
import com.valts.ob_forum_demo.servicios.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoServiceImpl implements CursoService {

    @Autowired
    private CursoRepository cursoRepo;


    @Override
    public List<Curso> findAll() {
        return cursoRepo.findAll();
    }

    @Override
    public Curso findOne(Long id) {
        Optional<Curso> cursoOpt = cursoRepo.findById(id);
        if (cursoOpt.isPresent()) {
            return cursoOpt.get();
        }

        return null;
    }

    @Override
    public Curso addCurso(Curso curso) {
        return cursoRepo.save(curso);
    }

    @Override
    public Curso updateCurso(Long id, Curso cursoNew) {
        Optional<Curso> cursoOpt = cursoRepo.findById(id);
        if (cursoOpt.isPresent()) {
                cursoNew.setId(id);
                return cursoRepo.save(cursoNew);

        }
        return null;
    }

    @Override
    public void deleteCurso(Long id) {
        Optional<Curso> cursoOpt = cursoRepo.findById(id);
        if (cursoOpt.isPresent()) {
            cursoRepo.deleteById(id);
        }

    }

    @Override
    public void deleteAllCursos() {
        cursoRepo.deleteAll();

    }


}
