package com.ashley.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashley.model.Role;

public interface RoleRepo extends JpaRepository<Role, Integer>{

}
