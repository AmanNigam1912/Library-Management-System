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

@Entity
public class BookReservation {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private long requestId;
		
	@Column
	private Date requestDate;
	
	@Column
	private Date tillDate;
	
	@Column
	private String requestStatus;
	
	@ManyToOne
	@JoinColumn(name="userLibraryId")
	private LibraryUserAccount libUserAcc;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="bookId")
	private Book book;
	
	public BookReservation() {
		
	}

	public long getRequestId() {
		return requestId;
	}

	public void setRequestId(long requestId) {
		this.requestId = requestId;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public String getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}

	public LibraryUserAccount getLibUserAcc() {
		return libUserAcc;
	}

	public void setLibUserAcc(LibraryUserAccount libUserAcc) {
		this.libUserAcc = libUserAcc;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Date getTillDate() {
		return tillDate;
	}

	public void setTillDate(Date tillDate) {
		this.tillDate = tillDate;
	}

}
