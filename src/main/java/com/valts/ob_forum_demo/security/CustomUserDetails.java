package com.valts.ob_forum_demo.security;

import org.springframework.security.core.userdetails.UserDetails;

// Get email instead of username through UserDetails
public interface CustomUserDetails extends UserDetails {
    String getEmail();
}
