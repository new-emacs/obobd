package com.bisone.saiku.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.Set;

public class UserDetailsServices implements UserDetailsService {
    /**
     * Logger for this class
     */
    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServices.class);

    public UserDetails loadUserByUsername(String userName)  throws UsernameNotFoundException, DataAccessException {

        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        authorities.add(new  SimpleGrantedAuthority("ROLE_USER"));

        return   new  UserToken("admin","admin" , authorities);
    }
}