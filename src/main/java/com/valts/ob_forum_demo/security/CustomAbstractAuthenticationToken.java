package com.valts.ob_forum_demo.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Principal;
import java.util.Collection;

public abstract class CustomAbstractAuthenticationToken extends AbstractAuthenticationToken implements Authentication, CredentialsContainer {

    /**
     * Creates a token with the supplied array of authorities.
     *
     * @param authorities the collection of <tt>GrantedAuthority</tt>s for the principal
     *                    represented by this authentication object.
     */
    public CustomAbstractAuthenticationToken(Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
    }

    public String getName() {
        if (this.getPrincipal() instanceof CustomUserDetails) {
            return ((CustomUserDetails) this.getPrincipal()).getEmail();
        }
        if (this.getPrincipal() instanceof AuthenticatedPrincipal) {
            return ((AuthenticatedPrincipal) this.getPrincipal()).getName();
        }
        if (this.getPrincipal() instanceof Principal) {
            return ((Principal) this.getPrincipal()).getName();
        }
        return (this.getPrincipal() == null) ? "" : this.getPrincipal().toString();
    }
}
