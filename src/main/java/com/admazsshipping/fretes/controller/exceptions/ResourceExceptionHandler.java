package com.admazsshipping.fretes.controller.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.admazsshipping.fretes.personal_exceptions.ConstraintUniqueException;
import com.admazsshipping.fretes.personal_exceptions.DataIntegrityException;
import com.admazsshipping.fretes.personal_exceptions.DatabaseException;
import com.admazsshipping.fretes.personal_exceptions.InesperedException;
import com.admazsshipping.fretes.personal_exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;


@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationError> validation(MethodArgumentNotValidException e, HttpServletRequest request){
		HttpStatus status=HttpStatus.UNPROCESSABLE_ENTITY;//422->Entidade não pôde ser processada
		
		ValidationError err=new ValidationError();
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Validation exception");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		
		for(FieldError f: e.getBindingResult().getFieldErrors()) {
			err.addError(f.getField(), f.getDefaultMessage());
		}		
		
		return ResponseEntity.status(status).body(err);
	}	
	
	@ExceptionHandler(ConstraintUniqueException.class)
	public ResponseEntity<ValidationError> validationUnique(ConstraintUniqueException e, HttpServletRequest request){
		HttpStatus status=HttpStatus.UNPROCESSABLE_ENTITY;//422->Entidade não pôde ser processada
		
		ValidationError err=new ValidationError();
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Validation exception");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		
		for(var f: e.get_listaErros()) {
			err.addError(f.getFieldName(), f.getMessage());
		}		
		
		return ResponseEntity.status(status).body(err);
	}	
	
	//DataIntegrityException
	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<ValidationError> validacaoIntegridadeFrete(DataIntegrityException e, HttpServletRequest request){
		HttpStatus status=HttpStatus.UNPROCESSABLE_ENTITY;//422->Entidade não pôde ser processada
		
		ValidationError err=new ValidationError();
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Dados inconsistentes");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		
		for(var f: e.get_listaErros()) {
			err.addError(f.getFieldName(), f.getMessage());
		}		
		
		return ResponseEntity.status(status).body(err);
	}	


	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<StandardError> illegalArgument(IllegalArgumentException e, HttpServletRequest request){
		HttpStatus status=HttpStatus.BAD_REQUEST;//400
		
		StandardError err=new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Bad Request");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		
		return ResponseEntity.status(status).body(err);
	}		
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> entityNotFound(ResourceNotFoundException e, HttpServletRequest request){
		HttpStatus status=HttpStatus.NOT_FOUND;//404
		
		StandardError err=new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Resource not found");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		
		return ResponseEntity.status(status).body(err);
	}	
	
	//JdbcSQLIntegrityConstraintViolationException
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request){
		HttpStatus status=HttpStatus.BAD_REQUEST;//400
		
		StandardError err=new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Database exception");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		
		return ResponseEntity.status(status).body(err);
	}
	
	//InesperedException
	@ExceptionHandler(InesperedException.class)
	public ResponseEntity<StandardError> erroInesperado(InesperedException e, HttpServletRequest request){
		HttpStatus status=HttpStatus.BAD_REQUEST;//400
		
		StandardError err=new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Ocorreu um erro!");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		
		return ResponseEntity.status(status).body(err);
	}
	
}
