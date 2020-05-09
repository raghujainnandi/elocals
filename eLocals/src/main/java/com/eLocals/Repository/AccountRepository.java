package com.eLocals.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eLocals.Entity.UserAccount;

public interface AccountRepository extends JpaRepository<UserAccount,Long>
{

	UserAccount findByEmail(String email);

}
