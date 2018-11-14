package com.bookstore.dto.request;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonInclude;
/**
 * @author mohammadnaushad
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PurchaseBookRequestDto implements Serializable{

	private static final long serialVersionUID = 3607184522856699049L;
	
	@NotEmpty
	private String isbn;
	
	@NotEmpty
	private String title;
	
	@NotEmpty
	private String author;
	
	@NotNull
	@Positive
	private Integer quantity;

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
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

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "PurchaseBookDto [isbn=" + isbn + ", title=" + title + ", author=" + author + ", quantity=" + quantity
				+ "]";
	}
	
	

}
