package com.tolgaaksoy.feleceToDoApp.repository;

import java.util.Optional;

import com.tolgaaksoy.feleceToDoApp.models.ERole;
import com.tolgaaksoy.feleceToDoApp.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(ERole name);

}
