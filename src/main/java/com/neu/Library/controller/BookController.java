package com.neu.Library.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.neu.Library.dao.BookDao;
import com.neu.Library.dao.CirculationDao;
import com.neu.Library.dao.LibraryUserDao;
import com.neu.Library.exception.BookException;
import com.neu.Library.exception.LibraryUserException;
import com.neu.Library.json.JsonUtils;
import com.neu.Library.pdfView.AdminPdfView;
import com.neu.Library.pojo.Book;
import com.neu.Library.pojo.BookCirculation;
import com.neu.Library.pojo.BookReservation;
import com.neu.Library.pojo.LibraryUser;
import com.neu.Library.pojo.LibraryUserAccount;
import com.neu.Library.validator.BookValidate;
import com.neu.Library.validator.UserBookRequestValidator;

@Controller
public class BookController {
	
	@Autowired
	BookDao bookDao;
	
	@Autowired
	LibraryUserDao libUserDao;
	
	@Autowired
	CirculationDao bookCirculationDAO;
	
	@Autowired
	BookValidate bookValidate;
	
	@Autowired
	UserBookRequestValidator userBookValidate;	
	
	
	@RequestMapping(value= "/admin/addBooks", method = RequestMethod.GET)
	protected ModelAndView addBooksForm() throws Exception {
		return new ModelAndView("addBooks", "book", new Book());
	}
	
	@RequestMapping(value = "/admin/addBooks", method = RequestMethod.POST)
	public ModelAndView bookUpload(HttpServletRequest request, @ModelAttribute("book") Book book, BindingResult result)
	throws IllegalStateException, IOException, BookException {
		
		HttpSession session = request.getSession();
		
		bookValidate.validate(book, result);
		
		if(result.hasErrors())
			return new ModelAndView("addBooks", "book", book);
		
		String localPath = "C:\\Users\\aman9\\Desktop\\NEU\\Web Tools\\Project\\images";
		
		
		String photoNewName = generateFileName(book.getPhoto());
		
		book.setPhotoFile(photoNewName);
		
		book.getPhoto().transferTo(new File(localPath, photoNewName));
		
		
		try {
			File file = new File(localPath);
			FileOutputStream fos = new FileOutputStream(file);
			fos.close();
					
		}catch(Exception e) {
			
		}
				
		try {
			
			bookDao.addBook(book);
					
		}catch (IllegalStateException e) {
			System.out.println("*** IllegalStateException: " + e.getMessage());
		} 
			
		session.setAttribute("book",book);
		
		return new ModelAndView("admin-home");
		
	}
	
	private String generateFileName(MultipartFile multipart) {
		return new Date().getTime() + "-" + multipart.getOriginalFilename().replace(" ", "-"); 
	}
	
	@RequestMapping(value= "/user/addBookRequest", method = RequestMethod.GET)
	public ModelAndView getBooks(HttpServletRequest request, ModelMap modelMap) throws Exception {
		List<Book> availableBooks = new ArrayList<Book>();
		modelMap.addAttribute("reserveBook", new BookReservation());
		try {
			availableBooks = bookDao.getAvailableBook();
			for(Book b:availableBooks) {
				System.out.println("Book name: "+b.getBookId());
			}			
		}catch (BookException e) {
			e.printStackTrace();
		}
		ModelAndView model = new ModelAndView();

		model.addObject("availableBooks", availableBooks);

		model.setViewName("availableBooks");
		
		return model;
	}
	
	@RequestMapping(value = "/user/addBookRequest", method = RequestMethod.POST)
	public ModelAndView registerBookRequest(HttpServletRequest request,
			@ModelAttribute("reserveBook") BookReservation reserveBook, BindingResult result) throws Exception {
		HttpSession session = request.getSession();
		
		ModelAndView model = new ModelAndView();
		LibraryUser lu =(LibraryUser) request.getSession().getAttribute("user");
		LibraryUserAccount libUserAcc = (LibraryUserAccount)libUserDao.getLibUserAcc(lu);
		
		reserveBook.setLibUserAcc(libUserAcc);
		
		Long bookId = Long.parseLong(request.getParameter("bookId"));
		Book b = bookDao.getBookRequested(bookId);
		b.setAvailability("no");
		bookDao.updateBookAvailability(b);
		reserveBook.setBook(b);
		
		SimpleDateFormat in = new SimpleDateFormat("yyyy-MM-dd");
		String requestDt= request.getParameter("request_Date");
		Date reqDt=in.parse(requestDt);
		
		String tillDate= request.getParameter("till_Date");
		Date td = in.parse(tillDate);
		
		System.out.println("request date: "+reqDt);
		System.out.println("till date: "+td);
		reserveBook.setRequestDate(reqDt);
		reserveBook.setTillDate(td);
		BookReservation r = bookDao.createReservation(reserveBook);
		
		model.addObject("requestedBook", r.getBook());
		model.addObject("requestNumber", r);
		model.addObject("reserveBook", r);
		List<BookCirculation> bookCirculationList = bookCirculationDAO.getBooksInCirculation(libUserAcc);

		session.setAttribute("bookcirculationlist", bookCirculationList);
		model.setViewName("userHome");
		
		return model;
	}
	
	@RequestMapping(value = "/admin/returnbook", method = RequestMethod.GET)
	protected ModelAndView returnBookform(HttpServletRequest request) throws Exception {
		List<BookCirculation> result = bookCirculationDAO.getAllBooksInCirculation();
		
		return new ModelAndView("returnBook", "bookcirculationlist", result);
	}

	
	@RequestMapping(value="/admin/report.pdf", method = RequestMethod.POST)
	public View getReport() throws Exception {
		
	
		List<BookCirculation> result = bookCirculationDAO.getAllBooksInCirculation();
		

		View view = new AdminPdfView(result);
		
		return view;
	}
	
	@RequestMapping(value = "/admin/searchBookById", method = RequestMethod.POST)
	protected ModelAndView getBookbyID(HttpServletRequest request) throws Exception {
		
		Long bookId=Long.parseLong(request.getParameter("bookid"));
		System.out.println("Inside getBookid" + bookId);
		

		ModelAndView model = new ModelAndView();
		BookCirculation bc = bookCirculationDAO.getBookCirculationByBookID(bookId);

		
		if (bc == null) {
			model.setViewName("bookNotFound");
			return model;
		}


		return new ModelAndView("returnBookAdmin", "bookCirculation", bc);
		

	}

	@RequestMapping(value = "/admin/confirmbookreturn", method = RequestMethod.POST)
	public ModelAndView confirmBookReturn(HttpServletRequest request) throws Exception {
		System.out.println("Inside Return book");
		HttpSession session = request.getSession();

		Long bookid = Long.parseLong(request.getParameter("bookid"));
		
		BookCirculation updatedbc = bookCirculationDAO.getBookCirculationByBookID(bookid);
				

		Book b = updatedbc.getBook();
		b.setAvailability("yes");
		bookDao.updateBookAvailability(b);

		if (updatedbc.getDueDate().before(new Date())) {
			LibraryUserAccount libUserAcc = updatedbc.getLibUserAcc();			
			libUserDao.updateLibraryMember(libUserAcc);
		}

		updatedbc.setReturnedDate(new Date());
		updatedbc.setBookStatus("Available");
		bookCirculationDAO.updateBookCirculation(updatedbc);

		return new ModelAndView("returnSuccess");
	}
	
	
}
