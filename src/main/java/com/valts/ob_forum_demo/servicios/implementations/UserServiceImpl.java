package com.valts.ob_forum_demo.servicios.implementations;

import com.valts.ob_forum_demo.models.Curso;
import com.valts.ob_forum_demo.models.User;
import com.valts.ob_forum_demo.repos.CursoRepository;
import com.valts.ob_forum_demo.repos.UserRepository;
import com.valts.ob_forum_demo.servicios.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private CursoRepository cursoRepo; // TODO: gotta go aswell



//    User save method / gets userdto passed
//      extract User from User dto
//      encrypt password, then set password

//      Set Role
//    Role role roleService.findByName("USER");
//    Set<Role> roleSet = new HashSet<>();
//    roleSet.add(role);

//    if mail is adminmail -> add admin role
//    if(user.getEmail().split("@")[1].equals("adminmail.com")){
//        role = roleService.findByName("ADMIN");
//        roleSet.add(role);
//    }

//    user.setRoles
//    save user userrepo



//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByUsername(username);
//        if(user == null){
//            throw new UsernameNotFoundException("Invalid username or password.");
//        }
//        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));
//    }
//
//    private Set<SimpleGrantedAuthority> getAuthority(User user) {
//        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
//        user.getRoles().forEach(role -> {
//            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
//        });
//        return authorities;
//    }
//
//    public List<User> findAll() {
//        List<User> list = new ArrayList<>();
//        userRepository.findAll().iterator().forEachRemaining(list::add);
//        return list;
//    }




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
        return userRepo.save(user);
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
