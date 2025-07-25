package com.techtips.uzbekTechTips.services;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.techtips.uzbekTechTips.model.Users;
import com.techtips.uzbekTechTips.repositories.UsersRepository;

@Service
public class UserService {

    @Autowired
    private UsersRepository usersRepo;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JWTService jwtService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);


    public String register(Users user){
        user.setPassword(encoder.encode(user.getPassword()));
        usersRepo.save(user);
        return user.toString();
    }

    public String deleteAll(){
        usersRepo.deleteAll();
        return "deleted";
    }

    public String verify(Users user){
        Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        
        if(auth.isAuthenticated()){
            return jwtService.generateToken(user.getUsername());
        }

        return "fail";
    }

}
