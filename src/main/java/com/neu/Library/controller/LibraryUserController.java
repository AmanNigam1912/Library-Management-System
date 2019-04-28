package com.neu.Library.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.neu.Library.dao.LibraryUserDao;
import com.neu.Library.exception.LibraryUserException;
import com.neu.Library.pojo.Authority;
import com.neu.Library.pojo.LibraryUser;
import com.neu.Library.pojo.LibraryUserAccount;
import com.neu.Library.validator.UserValidate;


@Controller
@RequestMapping("/*")
public class LibraryUserController {

	@Autowired
	LibraryUserDao libUserDao;
	
	@Autowired
	UserValidate userValidator;
	

	
	@RequestMapping(value = "/addLibraryUser", method = RequestMethod.GET)
	protected ModelAndView viewUserForm() throws LibraryUserException{
		ModelAndView mv = new ModelAndView();
		mv.addObject("libraryUser", new LibraryUser());
		mv.setViewName("addUser");
		return mv;
	}
	
	@RequestMapping(value = "/addLibraryUser", method = RequestMethod.POST)
	public ModelAndView signUpUser(@ModelAttribute("libraryUser") LibraryUser libraryUser, HttpServletRequest request, BindingResult result) 
			throws LibraryUserException{
		HttpSession session = request.getSession(); 
		
		
		userValidator.validate(libraryUser, result);
		
		if(result.hasErrors())
			return new ModelAndView("addUser", "libraryUser", libraryUser);

		
		LibraryUserAccount libUserAcc = new LibraryUserAccount();
		libUserAcc.setFine(0.0);
		libraryUser.setLibUserAcc(libUserAcc);
	
		System.out.println("inside LibraryUserController");

		libraryUser.setRole("user");
		
		libUserDao.signUp(libraryUser);	
		
		
		return new ModelAndView ("home");
	}
	
}
