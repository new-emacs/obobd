package com.bisone.saiku.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by will on 15-9-6.
 */
public class SessionService extends org.saiku.web.service.SessionService {

    private static final Logger log = LoggerFactory.getLogger(SessionService.class);

    private AuthenticationManager authenticationManager;

    public void setAuthenticationManager(AuthenticationManager auth) {
        this.authenticationManager = auth;
    }

    /* (non-Javadoc)
	 * @see org.saiku.web.service.ISessionService#authenticate(javax.servlet.http.HttpServletRequest, java.lang.String, java.lang.String)
	 */
    public void authenticate(HttpServletRequest req, String username, String password) {
        try {
            PreAuthenticatedAuthenticationToken token = new PreAuthenticatedAuthenticationToken(username, password);
            token.setDetails(new WebAuthenticationDetails(req));
            Authentication authentication = authenticationManager.authenticate(token);
            log.debug("Logging in with [{}]", authentication.getPrincipal());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        catch (BadCredentialsException bd) {
            throw new RuntimeException("Authentication failed for: " + username, bd);
        }

    }
}
