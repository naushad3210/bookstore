package com.bookstore.service;

import java.util.List;

import com.bookstore.domain.BookDetails;
import com.bookstore.dto.request.BookStoreRequestDto;
import com.bookstore.dto.response.MediaCoverageResponseDto;

/**
 * @author mohammadnaushad
 *
 */
public interface IBookStoreService {
	
	public List<BookDetails> getBook(String searchField,String searchValue);
	public BookDetails addBook(BookStoreRequestDto bookStoreDto);
	public List<MediaCoverageResponseDto> getMediaCoverage(String searchField,String searchValue);

}
