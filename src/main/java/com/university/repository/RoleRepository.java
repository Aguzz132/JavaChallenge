package com.university.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.university.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{
	
	Role findByName(String name);
	
}
