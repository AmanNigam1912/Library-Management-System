package com.neu.Library.validator;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.neu.Library.dao.LibraryUserDao;
import com.neu.Library.pojo.LibraryUser;


public class UserValidate implements Validator {
	
	@Autowired
	@Qualifier("libUserDao")
	LibraryUserDao libUserDao;
	
	@Override
	public boolean supports(Class clazz) {
		return clazz.equals(LibraryUser.class);
	}
	
	@Override
	public void validate(Object obj, Errors errors) {
		LibraryUser user = (LibraryUser)obj;
		
		System.out.println("inside user validate");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "error.invalid.firstName", "First Name required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "error.invalid.lastName", "Last Name required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "error.invalid.email", "Email required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "error.invalid.username", "Username required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.invalid.password", "Password required");
		
		if(errors.hasErrors())
			return;
		
		String username = user.getUsername();
		System.out.println("username: "+username);
		
		
		Pattern pattern = Pattern.compile("[^[a-z]+[A-Z]+]");
		
		if(pattern.matcher(user.getFirstName()).matches()){
			 errors.rejectValue("firstName", "firstName-invalid", "Enter valid value");
		 }
		 if(pattern.matcher(user.getLastName()).matches()){
			 errors.rejectValue("lastName", "lastName-invalid", "Enter valid value");
		 }
		 if(pattern.matcher(user.getUsername()).matches()){
			 errors.rejectValue("username", "username-invalid", "Enter valid value");
		 }
		 
		 Pattern patternEmail = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
		 
		 if(!patternEmail.matcher(user.getEmail()).matches()) {
			 errors.rejectValue("email", "email-invalid", "Enter valid value: Allowed A-Z, a-z, 0-9, may contain(. , - , _)");
		 }
		 
		 Pattern patternPassword = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");
		 
		 if(!patternPassword.matcher(user.getPassword()).matches()) {
			 errors.rejectValue("password", "password-invalid", "Enter valid value: Allowed 8 chars, no space/tab, atleast(one digit, one lower char, one upper char, one special char)");
		 }
				
	}
	
}
