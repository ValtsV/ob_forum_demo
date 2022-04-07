package com.valts.ob_forum_demo.controllers;

import com.valts.ob_forum_demo.dto.RegistrationDTO;
import com.valts.ob_forum_demo.dto.UserAuthDTO;
import com.valts.ob_forum_demo.models.User;
import com.valts.ob_forum_demo.repos.UserRepository;
import com.valts.ob_forum_demo.security.JwtUtil;
import com.valts.ob_forum_demo.servicios.implementations.RegistrationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class AuthController {

    @Autowired
    private RegistrationServiceImpl registrationService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/foro/auth/register")
    public ResponseEntity<String> registrate(@RequestBody RegistrationDTO registrationDTO) {

        Optional<User> userOptional = userRepository.findByEmail(registrationDTO.getEmail());
        if (userOptional.isPresent()) {
            return ResponseEntity.status(409).body("¡Email ya esta registrado!");
        }

        String successMessage =  registrationService.signUpUser(registrationDTO);
        return ResponseEntity.ok(successMessage);
    }

    @PostMapping("/foro/auth/login")
    public ResponseEntity<String> login(@RequestBody UserAuthDTO userAuthDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userAuthDTO.getEmail(), userAuthDTO.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtil.generateJwtToken(authentication);

        return ResponseEntity.ok(jwt);
    }

//      Login
//    Post generateToken (login data)
//    final Authentication authentication = autenticationManager.authenticate(
//              new UsernamePassworduthenticationToken(
//                  logindata.getusername,
//                  logindata.getpassword
//            )
//    SecurityContextHolder.getContext().setAuthentication(authentication)
//    final string token = jwtTokenutil create jwt token
//    return new Authtoken(token)
}
