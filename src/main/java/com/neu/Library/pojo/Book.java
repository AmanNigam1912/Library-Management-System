package com.neu.Library.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Entity
public class Book {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private long bookId;
	
	@Column
	private String title;
	
	@Column
	private String author;
	
	@Column
	private String genre;
	
	@Column
	private String language;
	
	@Column
	private String isbn;
	
//	@Type(type="true_false")
	@Column
	private String availability;
	
	@Column
	private String photoFile;
	
	@Transient
	private CommonsMultipartFile photo;
	
	
	public Book(){
		
	}


	public long getBookId() {
		return bookId;
	}


	public void setBookId(long bookId) {
		this.bookId = bookId;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public String getGenre() {
		return genre;
	}


	public void setGenre(String genre) {
		this.genre = genre;
	}


	public String getLanguage() {
		return language;
	}


	public void setLanguage(String language) {
		this.language = language;
	}


	public String getIsbn() {
		return isbn;
	}


	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}




	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	public CommonsMultipartFile getPhoto() {
		return photo;
	}


	public void setPhoto(CommonsMultipartFile photo) {
		this.photo = photo;
	}


	public String getPhotoFile() {
		return photoFile;
	}


	public void setPhotoFile(String photoFile) {
		this.photoFile = photoFile;
	}

	
}
