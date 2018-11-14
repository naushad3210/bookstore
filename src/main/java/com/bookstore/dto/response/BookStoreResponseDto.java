package com.bookstore.dto.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author mohammadnaushad
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookStoreResponseDto implements Serializable {

	private static final long serialVersionUID = 7746022943419698556L;
	private Long id;
	private String isbn;
	private String title;
	private String author;
	private Double price;
	private Integer quantity;
	private String mediaCoverageTitle;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getMediaCoverageTitle() {
		return mediaCoverageTitle;
	}
	public void setMediaCoverageTitle(String mediaCoverageTitle) {
		this.mediaCoverageTitle = mediaCoverageTitle;
	}
	
	
	@Override
	public String toString() {
		return "BookStoreResponseDto [id=" + id + ", isbn=" + isbn + ", title=" + title + ", author=" + author
				+ ", price=" + price + ", quantity=" + quantity + ", mediaCoverageTitle=" + mediaCoverageTitle + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
		result = prime * result + ((mediaCoverageTitle == null) ? 0 : mediaCoverageTitle.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof BookStoreResponseDto))
			return false;
		BookStoreResponseDto other = (BookStoreResponseDto) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (isbn == null) {
			if (other.isbn != null)
				return false;
		} else if (!isbn.equals(other.isbn))
			return false;
		if (mediaCoverageTitle == null) {
			if (other.mediaCoverageTitle != null)
				return false;
		} else if (!mediaCoverageTitle.equals(other.mediaCoverageTitle))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
	
}
