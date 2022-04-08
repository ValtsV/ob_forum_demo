package com.valts.ob_forum_demo.servicios.implementations;

import com.valts.ob_forum_demo.dto.RegistrationDTO;
import com.valts.ob_forum_demo.models.Role;
import com.valts.ob_forum_demo.models.User;
import com.valts.ob_forum_demo.models.UserRole;
import com.valts.ob_forum_demo.repos.UserRepository;
import com.valts.ob_forum_demo.servicios.RegistrationService;
import com.valts.ob_forum_demo.servicios.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;

    @Override
    public String signUpUser(RegistrationDTO registrationDTO) {


        User user = new User();

        user.setUsername(registrationDTO.getUsername());
        user.setEmail(registrationDTO.getEmail());
        user.setAvatar(registrationDTO.getAvatar());
        user.setPassword(registrationDTO.getPassword());
//        check if email matches admin email, if true, set user role - admin
//        user.setRole(UserRole.USER);

        String encodedPassword = passwordEncoder.encode(user.getPassword());

        user.setPassword(encodedPassword);

        Role role = roleService.findByName("USER");
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role);

        if(user.getEmail().split("@")[1].equals("admin.com")){
            role = roleService.findByName("ADMIN");
            roleSet.add(role);
        }

        user.setRoles(roleSet);

        userRepository.save(user);

        return "Success";
    }


}
