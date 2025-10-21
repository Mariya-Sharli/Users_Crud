package com.js.RestBasic_Crud.exception;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.js.employee_crud.exception.DataNotFoundException;

import io.micrometer.core.ipc.http.HttpSender.Response;

@RestControllerAdvice
public class GlobalExceptionHandlerr {
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public String handle(MethodArgumentNotValidException e)
	{
		return e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
	}
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Map<String,Object>> Handle400()
	{
		Map<String,Object> map = new LinkedHashMap<>();
		map.put("message", "Bad Request!");
		return ResponseEntity.status(400).body(map);
		
	}
	@ExceptionHandler(NoResourceFoundException.class)
	public ResponseEntity<Map<String,Object>> Handle404()
	{
		Map<String,Object> map = new LinkedHashMap<>();
		map.put("message", "Path does not Exists!!!!!!!!!");
		return ResponseEntity.status(404).body(map);
		
	}
	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<Map<String,Object>> Handle405()
	{
		Map<String,Object> map = new LinkedHashMap<>();
		map.put("message", "Method not Allowed!");
		return ResponseEntity.status(405).body(map);
		
	}

	
	@ExceptionHandler(DataNotFoundException.class)
	public ResponseEntity<Map<String,Object>> Handle_404()
	{
		Map<String,Object> map = new LinkedHashMap<>();
		map.put("message", "Path does not Exists");
		return ResponseEntity.status(404).body(map);
		
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String,Object>> Handle_500()
	{
		Map<String,Object> map = new LinkedHashMap<>();
		map.put("message", "Iternal Server error!!!");
		return ResponseEntity.status(500).body(map);
		
	}
}
