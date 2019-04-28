package com.neu.Library.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.neu.Library.exception.RequestException;
import com.neu.Library.pojo.BookReservation;
import com.neu.Library.pojo.LibraryUserAccount;



public class RequestDao extends DAO {

	public List<BookReservation> getPendingRequest(String status) throws Exception {
		try{
			begin();
			Query q = getSession().createQuery("from BookReservation where requestStatus = :status");
			q.setString("status", status);
			List<BookReservation> requests = q.list();
			commit();
			return requests;
		}catch(HibernateException e){
			rollback();
			throw new RequestException("Exception while retreiving requests: " + e.getMessage());
		}
	}
	
	public BookReservation getRequest(Long id) throws Exception{
		try{
			begin();
			Query q = getSession().createQuery("from BookReservation where requestId = :id");
			q.setLong("id", id);
			BookReservation r = (BookReservation) q.uniqueResult();
			commit();
			return r;
		}catch(HibernateException e){
			rollback();
			throw new RequestException("Exception while retreiving requests 1: " + e.getMessage());
		}
	}
	
	public void updateStatus(BookReservation b, String status) throws Exception{
		try{
			begin();
			b.setRequestStatus(status);
			getSession().update(b);
			commit();
		}catch(HibernateException e){
			throw new RequestException("Exception while updating request: " + e.getMessage());
		}
	}

	public List<BookReservation> getUserRequests(LibraryUserAccount mem) throws Exception{
		try{
			begin();
			Query q = getSession().createQuery("from BookReservation br where libUserAcc.userLibraryId =:userLibId");
			q.setLong("userLibId", mem.getUserLibraryId());
			List<BookReservation> result = q.list();
			commit();
			return result;
		}catch(HibernateException e){
			rollback();
			throw new RequestException("Exception while retreiving requests: " + e.getMessage());
		}
	}
	
}
