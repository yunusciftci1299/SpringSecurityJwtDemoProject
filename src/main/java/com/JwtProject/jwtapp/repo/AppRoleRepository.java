package com.JwtProject.jwtapp.repo;

import com.JwtProject.jwtapp.domain.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppRoleRepository extends JpaRepository<AppRole,Long> {
    AppRole findByName(String name);
}
