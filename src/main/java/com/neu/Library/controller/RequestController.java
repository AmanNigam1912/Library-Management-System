package com.neu.Library.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import com.neu.Library.dao.BookDao;
import com.neu.Library.dao.CirculationDao;
import com.neu.Library.dao.LibraryUserDao;
import com.neu.Library.dao.RequestDao;
import com.neu.Library.pojo.Book;
import com.neu.Library.pojo.BookCirculation;
import com.neu.Library.pojo.BookReservation;
import com.neu.Library.pojo.LibraryUser;
import com.neu.Library.pojo.LibraryUserAccount;

@Controller
public class RequestController {
	
	@Autowired
	RequestDao bookRequestDao;
	
	@Autowired
	LibraryUserDao libUserDao;
	
	@Autowired
	CirculationDao bookCirculationDAO;
	
	@Autowired
	BookDao bookDao;
	
	@RequestMapping(value="/admin/viewRequests" , method=RequestMethod.GET)
	protected ModelAndView displayOpenRequests() throws Exception {

		ModelAndView model = new ModelAndView();
		List<BookReservation> bookRequests = bookRequestDao.getPendingRequest("Pending");
		model.addObject("bookRequests", bookRequests);
		model.setViewName("pendingRequests");
		return model;
	}
	
	@RequestMapping(value="/admin/viewmemberinfo" , method=RequestMethod.POST)
	public ModelAndView ViewMemberInfo(HttpServletRequest request) throws Exception{
		ModelAndView viewMemberModel = new ModelAndView();
		String userInfo = request.getParameter("userInfo");
		String approveRequest = request.getParameter("approveRequest");
		String rejectRequest = request.getParameter("rejectRequest");
		System.out.println("userInfo :"+userInfo);
		System.out.println("approveRequest :"+approveRequest);
		System.out.println("rejectRequest :"+rejectRequest);
		
		if(userInfo!=null && userInfo.equals("userInfo")) {
			Long libUserId= Long.parseLong(request.getParameter("libUserId"));
			System.out.println("libUserId: "+libUserId);
			LibraryUser libUser =  (LibraryUser)libUserDao.getLibUser(libUserId);
			viewMemberModel.addObject("libUser" , libUser);
			viewMemberModel.setViewName("viewUserDetails");
			return viewMemberModel;
		}
		else if(approveRequest!=null && approveRequest.equals("approveRequest")) {
			Long requestId = Long.parseLong(request.getParameter("requestId"));
			BookReservation br = bookRequestDao.getRequest(requestId);
			System.out.println("Request ID: " + br.getRequestId());
			bookRequestDao.updateStatus(br, "Approved");
			BookCirculation bc = new BookCirculation(br.getLibUserAcc(),br.getBook(), br.getRequestDate(), br.getTillDate());
			bc.setBookStatus("Issued");
			bc = bookCirculationDAO.addBookCirculation(bc);
			viewMemberModel.addObject("bc" , bc);
			viewMemberModel.setViewName("approveRequest");
			long libUserId = bc.getLibUserAcc().getUserLibraryId();
			LibraryUser libUser =  (LibraryUser)libUserDao.getLibUser(libUserId);
			
			try {
				sendEmail(libUser, bc,"issued");			
			}catch (Exception e) {
				
			}
			return viewMemberModel;
		}
		else if(rejectRequest!=null && rejectRequest.equals("rejectRequest")){
			Long requestId = Long.parseLong(request.getParameter("requestId"));
			System.out.println(requestId);
			BookReservation br = bookRequestDao.getRequest(requestId);
			System.out.println("Request ID: " + br.getRequestId());
			Book b = br.getBook();
			b.setAvailability("yes");;
			bookDao.updateBookAvailability(b);
			bookRequestDao.updateStatus(br, "Declined");
			BookCirculation bc = new BookCirculation(br.getLibUserAcc(),br.getBook(), br.getRequestDate(), br.getTillDate());
			br.getBook().setAvailability("yes");
			viewMemberModel.addObject("br" , br);
			viewMemberModel.setViewName("rejectRequest");
			
			long libUserId = bc.getLibUserAcc().getUserLibraryId();
			LibraryUser libUser =  (LibraryUser)libUserDao.getLibUser(libUserId);
			try {
				sendEmail(libUser, bc,"rejected");			
			}catch (Exception e) {
				
			}
			return viewMemberModel;
		}
		else
			return null;
	}
	
	
	@RequestMapping(value = "/user/viewRaisedRequests", method = RequestMethod.GET)
	public ModelAndView getUserRequests(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		LibraryUser u = (LibraryUser) session.getAttribute("user");
		LibraryUserAccount libUserAcc = u.getLibUserAcc();
		List<BookReservation> result = bookRequestDao.getUserRequests(libUserAcc);
		return new ModelAndView("bookRequested", "bookreservationlist", result);
	}
	
	private void sendEmail(LibraryUser libUser, BookCirculation bc,String status) throws EmailException {
		Email email = new SimpleEmail();
		email.setHostName("smtp.googlemail.com");
		email.setSmtpPort(465);
		
		email.setAuthenticator(new DefaultAuthenticator("buyeazee@gmail.com", "buyeazee@123"));
		email.setSSLOnConnect(true);
		email.setFrom("no-reply@msis.neu.edu");
		if(status=="issued")
		{
			email.setSubject("Book Issue request approved");
			email.setMsg("Your request to issue book titled: "+ bc.getBook().getTitle() +" by author: "+bc.getBook().getAuthor() + " is complete.\n Please visit the library and collect the issued book");
		}
		else {
			email.setSubject("Book Issue request rejected");
			email.setMsg("Your request to issue book titled: "+ bc.getBook().getTitle() +" by author: "+bc.getBook().getAuthor() + " has been rejected.\n We are sorry for this.");
		}
		email.addTo(libUser.getEmail());
		email.send();
	}
	
}
