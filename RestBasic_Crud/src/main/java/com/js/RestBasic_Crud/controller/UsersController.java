package com.js.RestBasic_Crud.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.js.RestBasic_Crud.RestBasicCrudApplication;
import com.js.RestBasic_Crud.DTO.DTOUsers;
import com.js.RestBasic_Crud.Entity.Users;
import com.js.RestBasic_Crud.exception.dataNotFoundException;
import com.js.RestBasic_Crud.service.UsersService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
@RestController
@RequestMapping("/users")
public class UsersController {
	
	private RestBasicCrudApplication restBasicCrudApplication;
	@Autowired
	private UsersService service;
	
	public UsersController(RestBasicCrudApplication restBasicCrudApplication) {
		// TODO Auto-generated constructor stub
		this.restBasicCrudApplication = restBasicCrudApplication;
	}
	
	@GetMapping
	public ResponseEntity<Map<String,Object>> Find(
			@RequestParam (defaultValue = "id") String sort,
			@RequestParam (defaultValue = "false") boolean desc,
			@RequestParam (defaultValue = "1") int page,
			@RequestParam (defaultValue = "3") int size,
			@RequestParam (required = false) String name)//filtered by name 
	{
		Map<String,Object> map = new LinkedHashMap<>();
		
		List<Users> user = service.FindUsers(sort,desc,page,size,name);
			
//		if(user.isEmpty())
//		{
//			map.put("status", "success");
//			map.put("code", 204);
//			map.put("message", "No User Found");
//			map.put("data", user);
//			return ResponseEntity.status(204).body(map);
//		}
//		return ResponseEntity.status(200).build();//this give only the message 
		map.put("status", "success");
		map.put("code", 200);
		map.put("message", "User Found");
		map.put("data", user);
		return ResponseEntity.ok(map);//this gives  the response in JSON
	}
	 @PostMapping
	public  ResponseEntity<Map<String,Object>> createUsers( @Valid @RequestBody Users user)
	{
//		 Users user = new Users();
//		 user.setName(duser.getName());
//		 user.setEmail(duser.getEmail());
		 Map<String,Object> map = new LinkedHashMap<>();
//		 try {
			 Users saveuser = service.add(user);
			 map.put("status", "success");
			 map.put("code", 201);
			 map.put("message","Data saved Successfully" );
			 map.put("data", saveuser);
			 return ResponseEntity.status(201).body(map);
//			 }
//		 catch(Exception e)
//		 {
//			 map.put("status","error");
//			 map.put("code", 500);
//			 map.put("message", "Internal server error");
//			 map.put("error",e.getMessage() );
//			 return ResponseEntity.status(500).body(map);
//		 }
		//return service.add(user);
	}
	
	 
	@DeleteMapping("/{id}")
	@Operation(summary = "Delete by id")
	public ResponseEntity<Map<String,Object>>DeleteUsers( @PathVariable Long id)
	{
		Map<String,Object> map = new LinkedHashMap<>();
		boolean del = service.delete(id);
		if(del)
		{
			map.put("Status", "success");
			map.put("message","Data Deleted Successfully");
			return ResponseEntity.status(200).body(map);
		}
		else
		{
			map.put("status", "error");
			map.put("message", "Data Not Found");
			
		}

		return ResponseEntity.status(404).body(map);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Map<String,Object>> FindUserById( @PathVariable Long id)
	{
		Map<String,Object> map = new LinkedHashMap<>();
		Optional<Users> userByid = service.findUsersByid(id);
		if(userByid.isPresent())
		{
			map.put("status", "success");
			map.put("code", 200);
			map.put("message", "Data fetched by id");
			map.put("data", userByid);
			return ResponseEntity.status(200).body(map);
		}
		else
		{
			map.put("status", "error");
			map.put("code", 404);
			map.put("message", "Data not found");
			return ResponseEntity.status(404).body(map);
			
		}
		
	}
	@PutMapping("/{id}")
//	@PatchMapping("/{id}")
	public ResponseEntity<Map<String,Object>> updateUsers(@Valid @RequestBody Users user,@PathVariable Long id)
	{
		Map<String,Object> map = new LinkedHashMap<>();
		
//		try {
			
			Users update = service.update(user,id);
			map.put("status", "success");
			map.put("code", 200);
			map.put("data",update);
			return ResponseEntity.ok(map);
//		}
//			catch(dataNotFoundException e) {
//			map.put("status", "error");
//			map.put("code", 404);
////			map.put("data", service.update(user,id));
//			return ResponseEntity.status(404).body(map);
//		}catch(Exception e)
//		{
//			map.put("status", "error");
//			map.put("code", 500);
////			map.put("data", service.update(user,id));
//			return ResponseEntity.status(500).body(map);
//		}
//		
	}
}
