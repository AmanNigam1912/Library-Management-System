package com.neu.Library.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;



@Entity
public class BookCirculation {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private long circulationId;
	
	@Column
	private Date issuedDate;
	
	@Column
	private Date returnedDate;
	
	@Column
	private String bookStatus;
	
	@Column
	private Date dueDate;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="bookId")
	private Book book;
	
	@ManyToOne
	@JoinColumn(name="userLibraryId")
	private LibraryUserAccount libUserAcc;
	
	public BookCirculation() {
		
	}

	public BookCirculation(
			LibraryUserAccount libUserAcc, 
			Book book, Date issuedDate, Date dueDate) {
		this.libUserAcc = libUserAcc;
		this.book = book;
		this.issuedDate = issuedDate;
		this.dueDate = dueDate;
	}
	
	public long getCirculationId() {
		return circulationId;
	}

	public void setCirculationId(long circulationId) {
		this.circulationId = circulationId;
	}

	public Date getIssuedDate() {
		return issuedDate;
	}

	public void setIssuedDate(Date issuedDate) {
		this.issuedDate = issuedDate;
	}

	public Date getReturnedDate() {
		return returnedDate;
	}

	public void setReturnedDate(Date returnedDate) {
		this.returnedDate = returnedDate;
	}

	public String getBookStatus() {
		return bookStatus;
	}

	public void setBookStatus(String bookStatus) {
		this.bookStatus = bookStatus;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public LibraryUserAccount getLibUserAcc() {
		return libUserAcc;
	}

	public void setLibUserAcc(LibraryUserAccount libUserAcc) {
		this.libUserAcc = libUserAcc;
	}
	
	
}
