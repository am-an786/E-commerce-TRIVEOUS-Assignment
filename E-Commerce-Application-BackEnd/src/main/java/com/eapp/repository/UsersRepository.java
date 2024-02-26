package com.eapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eapp.model.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer>{

	public Optional<Users> findByEmail(String email);

	public Boolean existsByEmail(String email);

	public Boolean existsByContact(String contact);

}
