package com.JwtProject.jwtapp.service;


import com.JwtProject.jwtapp.domain.AppRole;
import com.JwtProject.jwtapp.domain.AppUser;
import com.JwtProject.jwtapp.repo.AppRoleRepository;
import com.JwtProject.jwtapp.repo.AppUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService , UserDetailsService {

    private final AppUserRepository appUserRepository;
    private final AppRoleRepository appRoleRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = appUserRepository.findByUsername(username);
        if (user == null){
            log.error("User not found in db");
            throw new UsernameNotFoundException("user not found in db");
        }else {
            log.info("user found in db: {}",username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(appRole -> {
            authorities.add(new SimpleGrantedAuthority(appRole.getName()));
        });
        return new User(user.getUsername(), user.getPassword(),authorities);
    }

    @Override
    public AppUser saveUser(AppUser user) {
        log.info("saving new user {}",user.getName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return appUserRepository.save(user);
    }

    @Override
    public AppRole saveRole(AppRole role) {
        log.info("saving new role {}",role.getName());
        return appRoleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding role {} to user {}",roleName,username);
        AppUser user = appUserRepository.findByUsername(username);
        AppRole role=appRoleRepository.findByName(roleName);
        user.getRoles().add(role);

    }

    @Override
    public AppUser getUser(String username) {
        log.info("getUser {}",username);
        return appUserRepository.findByUsername(username);
    }

    @Override
    public List<AppUser> getUsers() {
        log.info("getUsers");
        return appUserRepository.findAll();
    }

}
