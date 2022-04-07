package com.valts.ob_forum_demo.servicios;

import com.valts.ob_forum_demo.dto.RegistrationDTO;
import org.springframework.stereotype.Service;

@Service
public interface RegistrationService {

    String signUpUser(RegistrationDTO registrationDTO);
}
