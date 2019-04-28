package com.neu.Library.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.neu.Library.dao.LibraryUserDao;
import com.neu.Library.pojo.LibraryUser;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	
	@Autowired
	LibraryUserDao libUserDao;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	

	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		request.getSession().invalidate();
		return "home";
	}
	
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public ModelAndView home(HttpServletRequest request) {
		request.getSession().invalidate();
		ModelAndView mv = new ModelAndView();
		mv.setViewName("Login");
		return mv;
	}
	
	@RequestMapping(value="/login" , method = RequestMethod.POST)
	public ModelAndView checkLogin(HttpServletRequest request) {
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		HttpSession session = (HttpSession) request.getSession();
		ModelAndView login = new ModelAndView();
		
		try {
			LibraryUser libUser= libUserDao.checkLogin(username, password);
			
			if(libUser==null) {
				System.out.println("Username or password does not exist");
				login.addObject("errorMessage", "Username/Password is incorrect");
				login.setViewName("errorPage");
				return new ModelAndView ("errorPage");
			}
			else if(libUser.getRole().equals("user")){
				System.out.println("inside role user");
				login.addObject("loginSuccess","Login successful");
				login.setViewName("home");
				session.setAttribute("user", libUser);
				return new ModelAndView (new RedirectView("/NigamAman/user/addBookRequest"));
			}
			else 
				
			{
				return new ModelAndView("admin-home");
			}
			
				
		}catch(Exception e) {

			return new ModelAndView ("errorPage");
		}
		
		
	}
	
}
