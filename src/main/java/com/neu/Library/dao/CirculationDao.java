package com.neu.Library.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.neu.Library.exception.BookException;
import com.neu.Library.pojo.BookCirculation;
import com.neu.Library.pojo.LibraryUserAccount;



public class CirculationDao extends DAO{

	public BookCirculation addBookCirculation(BookCirculation bc) throws BookException{
		try {
			begin();
			System.out.println("inside CirculationDAO - addBookCirculation");
			getSession().save(bc);
			commit();
			return bc;

		} catch (HibernateException e) {
			rollback();
			throw new BookException("Exception while creating user: " + e.getMessage());
		}
	}
	
	public List<BookCirculation> getBooksInCirculation(LibraryUserAccount m){
		
		System.out.println("inside CirculationDAO - getBooksIncirculation");
		System.out.println("User Id: "+ m.getUserLibraryId());
//		Criteria bookcirCrit  = getSession().createCriteria(BookCirculation.class);
//		Criteria memCrit = bookcirCrit.createCriteria("libraryMember");
//		LibraryMember mem = new LibraryMember();
//		mem.setMemberID(m.getMemberID());
//		mem.setLibraryUser(m.getLibraryUser());
//		memCrit.add(Example.create(mem));
//		List<BookCirculation> bookCirculationList = bookcirCrit.list();
		
		Query q = getSession().createQuery("from BookCirculation where libUserAcc.userLibraryId =:libUserId and returnedDate is null");
		q.setLong("libUserId", m.getUserLibraryId());
		List<BookCirculation> bookCirculationList = q.list();
		System.out.println("Size of circulation list: " + bookCirculationList.size());
		for(BookCirculation bb:bookCirculationList) {
			System.out.println("issued Date: "+bb.getIssuedDate());
			System.out.println("issued Date: "+bb.getReturnedDate());
		}
		//commit();
		return bookCirculationList;
	}
	
	
	public List<BookCirculation> getAllBooksInCirculation(){
		begin();
		Query q = getSession().createQuery("from BookCirculation where returnedDate is null");
		List<BookCirculation> result = q.list();
		System.out.println(result.size());
		commit();
		return result;
	}
	
	public BookCirculation getBookCirculationByBookID(long bookId){
		begin();
		Query q = getSession().createQuery("from BookCirculation where book.bookId = :bid and returnedDate is null");
		q.setLong("bid",bookId);
		BookCirculation bc = (BookCirculation) q.uniqueResult();
		commit();
		return bc;
	}
	
	public void updateBookCirculation(BookCirculation bc) throws BookException{
		try{
			begin();
			getSession().update(bc);
			commit();
		}catch(HibernateException e){
			rollback();
			throw new BookException("Exception while updating user occurence: " + e.getMessage());
		}
		
	}
	
	
}
