package com.eLocals.SecurityConfiguration;


import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;

import com.eLocals.Entity.UserAccount;
import com.eLocals.Repository.AccountRepository;


@Service
public class UserPrincipalDetailsService implements UserDetailsService {
    
	private AccountRepository userRepository;

    public UserPrincipalDetailsService(AccountRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
      UserAccount user = this.userRepository.findByEmail(s);
        if(user != null) {
        	String password = user.getPassword();
        
        UserPrincipal userPrincipal = new UserPrincipal(user);
        Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) userPrincipal.getAuthorities();
        org.springframework.security.core.userdetails.User securedUser = new org.springframework.security.core.userdetails.User
        		(s, password, true, true, true, true, authorities);
        return securedUser;
        }
        else {
        	throw new UsernameNotFoundException("Unable to find username");
        }
    }
}
