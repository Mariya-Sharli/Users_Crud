package com.js.RestBasic_Crud.service;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.query.Page;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.js.RestBasic_Crud.Entity.Users;
import com.js.RestBasic_Crud.repository.UsersRepository;
import com.js.RestBasic_Crud.exception.dataNotFoundException;

@Service
public class UsersService {
	@Autowired
	private UsersRepository repo;
	
	
	public List<Users> FindUsers(String sort,boolean desc,int page,int size,String name)
	{
		Sort sorting = desc?Sort.by(sort).descending():Sort.by(sort).ascending();
		PageRequest pagenation = PageRequest.of(page-1, size,sorting);
		
		List<Users> user = new ArrayList<Users>();
		if(name== null)
		{
			user = repo.findAll(pagenation).getContent();
		}
		else
		{
			user = repo.findByNameContainingIgnoreCase(name, pagenation);
		}
		if(user.isEmpty())
		{
			throw new dataNotFoundException(name); 
		}
		
		return user;
	}
	
	public Users add(Users user)
	{
		return repo.save(user);
	}
	
	public boolean delete(Long id)
	{
		if(repo.existsById(id))
		{
			repo.deleteById(id);
			return true;
		}
		return false;
	}
	
	public Optional<Users> findUsersByid(Long id)
	{
		return repo.findById(id);
	}
	
	
	public Users update(Users user,Long id)
	{
		Users olduser = repo.findById(id).orElseThrow(()->new dataNotFoundException("User not found "));
		if(user.getName() !=null)
			olduser.setName(user.getName());
		if(user.getEmail() !=null)
			olduser.setEmail(user.getEmail());
		 return repo.save(olduser);
		
	}
	

	
}
