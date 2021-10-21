package com.JwtProject.jwtapp.service;

import com.JwtProject.jwtapp.domain.AppRole;
import com.JwtProject.jwtapp.domain.AppUser;

import java.util.List;

public interface UserService {
    AppUser saveUser(AppUser user);
    AppRole saveRole(AppRole role);
    void addRoleToUser(String username, String roleName);
    AppUser getUser(String username);
    List<AppUser> getUsers();
}
