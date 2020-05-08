package com.eLocals.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eLocals.Entity.User;

public interface UserRepository extends JpaRepository<User ,Integer>{

	User findByEmail(String email);

}
