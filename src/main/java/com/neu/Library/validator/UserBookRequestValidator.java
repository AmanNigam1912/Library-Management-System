package com.neu.Library.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.neu.Library.pojo.BookReservation;



public class UserBookRequestValidator implements Validator {

	@Override
	public boolean supports(Class clazz) {
		return clazz.equals(BookReservation.class);
	}
	
	@Override
	public void validate(Object obj, Errors errors) {
		BookReservation br = (BookReservation)obj;
		
		if(br.getTillDate().before(br.getRequestDate()) || br.getTillDate().compareTo(br.getRequestDate()) == 0){
			errors.rejectValue("tillDate", "tillDate-invalid", "Please choose a valid date");
		}
	}
}
