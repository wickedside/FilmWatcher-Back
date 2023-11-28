package com.app.filmwatcher.service;

import com.app.filmwatcher.model.MyUserDetails;
import com.app.filmwatcher.model.User;
import com.app.filmwatcher.repository.RoleRepository;
import com.app.filmwatcher.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepo;
    private final RoleRepository roleRepo;

    public MyUserDetailsService(UserRepository userRepo, RoleRepository roleRepo) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username).get();
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new MyUserDetails(user, roleRepo);
    }
}