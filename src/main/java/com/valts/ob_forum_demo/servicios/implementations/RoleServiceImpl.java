package com.valts.ob_forum_demo.servicios.implementations;

import com.valts.ob_forum_demo.models.Role;
import com.valts.ob_forum_demo.repos.RoleRepository;
import com.valts.ob_forum_demo.servicios.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role findByName(String name) {
        return roleRepository.findRoleByName(name);
    }
}
