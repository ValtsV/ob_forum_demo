package com.valts.ob_forum_demo.servicios;

import com.valts.ob_forum_demo.models.Respuesta;
import com.valts.ob_forum_demo.models.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User findOne(Long id);

    User addOne(User user);

    User updateOne(Long id, User user);

    void deleteOne(Long id);

    void deleteAll();

}
