package com.neu.Library.validator;

import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.neu.Library.pojo.Book;



public class BookValidate implements Validator{
	
	@Override
	public boolean supports(Class clazz) {
		return clazz.equals(Book.class);
	}
	
	@Override
	public void validate(Object obj, Errors errors) {

		Book book = (Book)obj;
		
		System.out.println("inside user validate");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "error.invalid.title", "Title required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "author", "error.invalid.author", "Author required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "genre", "error.invalid.genre", "Genre required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "language", "error.invalid.language", "Language required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "isbn", "error.invalid.isbn", "Isbn required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "availability", "error.invalid.availability", "Availability required");
		
		if(errors.hasErrors())
			return;
		
		
		Pattern pattern = Pattern.compile("[^[a-z]+[A-Z]+]");
		
		if(pattern.matcher(book.getAuthor()).matches()){
			 errors.rejectValue("author", "author-invalid", "Enter valid value");
		 }
		if(pattern.matcher(book.getTitle()).matches()){
			 errors.rejectValue("title", "title-invalid", "Enter valid value");
		 }

		if(pattern.matcher(book.getLanguage()).matches()){
			 errors.rejectValue("language", "language-invalid", "Enter valid value");
		 }
		if(pattern.matcher(book.getIsbn()).matches()){
			 errors.rejectValue("isbn", "isbn-invalid", "Enter valid value");
		 }

		
	}
}
