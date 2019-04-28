package com.neu.Library.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;


import com.neu.Library.exception.BookException;
import com.neu.Library.pojo.Book;
import com.neu.Library.pojo.BookReservation;

public class BookDao extends DAO {
	
	public BookDao() {
		
	}

	public Book addBook(Book book) throws BookException{
		try {
			begin();
			System.out.println("inside DAO");
			getSession().save(book);
			commit();
			return book;

		} catch (HibernateException e) {
			rollback();
			throw new BookException("Exception while creating user: " + e.getMessage());
		}
	}
	
	public List<Book> getAvailableBook() throws BookException{
		try {
            begin();
            String hql = "from Book where availability= :avail";
            Query q = getSession().createQuery(hql);
            q.setString("avail", "yes");
            List<Book> books = q.list();
            commit();
            return books;
        } catch (HibernateException e) {
            rollback();
            throw new BookException("No books available", e);
        }
	}
	
	public Book getBookRequested(Long bookId) throws Exception{
		try{
			Query q = getSession().createQuery("from Book where bookId = :id");
			q.setLong("id" , bookId);
			Book b = (Book) q.uniqueResult();
			return b;
		}catch(HibernateException e){
			rollback();
			throw new BookException("Exception while retriving book: " + e.getMessage());
		}
	}
	
	public void updateBookAvailability(Book book) throws BookException{
		try{
			begin();
			getSession().update(book);
			commit();
		}
		catch(HibernateException e){
			rollback();
			throw new BookException("Exception while updating book availability: " + e.getMessage());
		}
	}
	
	public BookReservation createReservation(BookReservation reserveBook) throws Exception {
		try {
			begin();
			System.out.println("inside DAO");			
			reserveBook.setRequestStatus("Pending");
			System.out.println("Request date: "+reserveBook.getRequestDate());
			System.out.println("Till date: "+reserveBook.getTillDate());
			System.out.println("Status: "+reserveBook.getRequestStatus());
			getSession().save(reserveBook);
			commit();
			return reserveBook;

		} catch (HibernateException e) {
			rollback();
			throw new BookException("Exception while creating book request: " + e.getMessage());
		}
	}
	
}
