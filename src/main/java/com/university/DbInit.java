package com.university;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.university.model.Role;
import com.university.model.User;
import com.university.repository.RoleRepository;
import com.university.repository.UserRepository;

@Component
public class DbInit {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@PostConstruct
    private void postConstruct() {
		Role newAdminRole = new Role();
		Role newStudentRole = new Role();		
		
		newAdminRole.setName("ADMIN");
		newStudentRole.setName("STUDENT");
		
		roleRepository.save(newAdminRole);
		roleRepository.save(newStudentRole);
		
		Role adminRole = roleRepository.findByName("ADMIN");
		
        User adminUser = new User();
        adminUser.setUserName("admin");
        adminUser.setPassword(bCryptPasswordEncoder.encode("admin"));
        adminUser.setActive(1);
        adminUser = userRepository.save(adminUser);
        System.out.println(Arrays.asList(adminRole));
        System.out.println(new HashSet<Role>(Arrays.asList(adminRole)));
        adminUser.setRoles(new HashSet<Role>(Arrays.asList(adminRole)));
        userRepository.save(adminUser);
        
	}

}
