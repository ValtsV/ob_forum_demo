package com.valts.ob_forum_demo.servicios.implementations;

import com.valts.ob_forum_demo.models.Curso;
import com.valts.ob_forum_demo.models.User;
import com.valts.ob_forum_demo.repos.CursoRepository;
import com.valts.ob_forum_demo.repos.UserRepository;
import com.valts.ob_forum_demo.servicios.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepo;
    private CursoRepository cursoRepo; // TODO: gotta go aswell

    public UserServiceImpl(UserRepository userRepo, CursoRepository cursoRepo) {
        this.userRepo = userRepo;
        this.cursoRepo = cursoRepo;
    }

    @Override
    public List<User> findAll() {

        return userRepo.findAll();
    }

    @Override
    public User findOne(Long id) {
        Optional<User> userOpt = userRepo.findById(id);
        if (userOpt.isPresent()) {
            return userOpt.get();
        }

        return null;
    }

    @Override
    public User addOne(User user) {
        User savedUser = userRepo.save(user);
        return savedUser;
    }

    @Override
    public User updateOne(Long id, User userNew) {
        Optional<User> userOpt = userRepo.findById(id);
        if (userOpt.isPresent()) {
            userNew.setId(id);
            return userRepo.save(userNew);

        }
        return null;
    }

    @Override
    public void deleteOne(Long id) {
        Optional<User> userOpt = userRepo.findById(id);
        if (userOpt.isPresent()) {
            userRepo.deleteById(id);
        }
    }

    @Override
    public void deleteAll() {
        userRepo.deleteAll();
    }


//    TODO: This gotta go
    public User enrollInCurso(Long userId, Long cursoId) {
        Optional<Curso> cursoOpt = cursoRepo.findById(cursoId);

        Optional<User> userOpt = userRepo.findById(userId);
        if (userOpt.isPresent() && cursoOpt.isPresent()) {
            User user = userOpt.get();
            Curso curso = cursoOpt.get();
            user.getAttendedCursos().add(curso);
            return userRepo.save(user);

        }
        return null;
    }

}
