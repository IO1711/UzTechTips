package com.techtips.uzbekTechTips.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.techtips.uzbekTechTips.model.UserPrincipal;
import com.techtips.uzbekTechTips.model.Users;
import com.techtips.uzbekTechTips.repositories.UsersRepository;

@Service
public class MyUserDetailsService implements UserDetailsService{

    @Autowired
    private UsersRepository usersRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = usersRepo.findByUsername(username);

        if(user == null){
            throw new UsernameNotFoundException("User " + username + " not found");
        }

        return new UserPrincipal(user);
    }

    

}
