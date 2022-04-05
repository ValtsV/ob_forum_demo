package com.valts.ob_forum_demo.servicios.implementations;

import com.valts.ob_forum_demo.models.Modulo;
import com.valts.ob_forum_demo.repos.ModuloRepository;
import com.valts.ob_forum_demo.servicios.ModuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModuloServiceImpl implements ModuloService {

    @Autowired
    ModuloRepository moduloRepo;

    @Override
    public List<Modulo> findAll() {
        return moduloRepo.findAll();
    }

    @Override
    public Modulo findOne(Long id) {
        Optional<Modulo> moduloOpt = moduloRepo.findById(id);
        if (moduloOpt.isPresent()) {
            return moduloOpt.get();
        }

        return null;
    }

    @Override
    public Modulo addModulo(Modulo modulo) {
        return moduloRepo.save(modulo);
    }

    @Override
    public Modulo updateModulo(Long id, Modulo modulo) {
        Optional<Modulo> moduloOpt = moduloRepo.findById(id);
        if (moduloOpt.isPresent()) {
            modulo.setId(id);
            return moduloRepo.save(modulo);

        }
        return null;
    }

    @Override
    public void deleteModulo(Long id) {
        Optional<Modulo> moduloOpt = moduloRepo.findById(id);
        if (moduloOpt.isPresent()) {
            moduloRepo.deleteById(id);
        }
    }

    @Override
    public void deleteAllModulos() {
        moduloRepo.deleteAll();
    }
}
