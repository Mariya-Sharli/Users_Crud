package com.js.RestBasic_Crud.repository;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import com.js.RestBasic_Crud.Entity.Users;

public interface UsersRepository extends JpaRepository<Users, Long>{
	
	List<Users> findByNameContainingIgnoreCase(String name,PageRequest pagenation);

}
