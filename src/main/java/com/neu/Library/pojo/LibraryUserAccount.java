package com.neu.Library.pojo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class LibraryUserAccount {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private long userLibraryId;
	
	@Column
	private double fine;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="libUserAcc")
	private List<BookReservation> bookReserve;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="libUserAcc")
	private List<BookCirculation> bookCirculate;
	
	
	public LibraryUserAccount() {
		
	}

	public long getUserLibraryId() {
		return userLibraryId;
	}

	public void setUserLibraryId(long userLibraryId) {
		this.userLibraryId = userLibraryId;
	}

	public double getFine() {
		return fine;
	}

	public void setFine(double fine) {
		this.fine = fine;
	}

	public List<BookReservation> getBookReserve() {
		return bookReserve;
	}

	public void setBookReserve(List<BookReservation> bookReserve) {
		this.bookReserve = bookReserve;
	}

	public List<BookCirculation> getBookCirculate() {
		return bookCirculate;
	}

	public void setBookCirculate(List<BookCirculation> bookCirculate) {
		this.bookCirculate = bookCirculate;
	}

	
	
}
