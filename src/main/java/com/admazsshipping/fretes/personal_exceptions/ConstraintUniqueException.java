package com.admazsshipping.fretes.personal_exceptions;

import java.util.List;

import com.admazsshipping.fretes.controller.exceptions.FieldMessage;


//public class ConstraintUniqueException extends RuntimeException { //MethodArgumentNotValidException
public class ConstraintUniqueException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private List<FieldMessage> _listaErros;
	
	public ConstraintUniqueException(String msg, List<FieldMessage> listaErros) {
		super(msg);
		_listaErros = listaErros;
		//super(getParameter(), getBindingResult())
	}

	public List<FieldMessage> get_listaErros() {
		return _listaErros;
	}

}
