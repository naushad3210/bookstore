package com.bookstore.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.domain.BookDetails;
import com.bookstore.dto.request.BookStoreRequestDto;
import com.bookstore.dto.response.BookStoreResponseDto;
import com.bookstore.dto.response.MediaCoverageResponseDto;
import com.bookstore.dto.response.ResponseDto;
import com.bookstore.service.IBookStoreService;
import com.bookstore.wrapper.BookStoreWrapper;
/**
 * @author mohammadnaushad
 *
 */
@RestController
@RequestMapping("/bookstore")
public class BookStoreController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BookStoreController.class);
	
	@Autowired
	IBookStoreService bookService;
	
	@PostMapping(value = "/addbook",  headers = "Accept=application/json")
	public ResponseDto<BookStoreResponseDto> addBook(@RequestBody @Valid BookStoreRequestDto bookStoreDto) {
		LOGGER.info("-- Inside [BookStoreController] [addBook()] with [Data:{}]",bookStoreDto);
		
		BookDetails bookDetailsResponse = bookService.addBook(bookStoreDto);
		ResponseDto<BookStoreResponseDto> bookStoreResponse= new ResponseDto<>();
		bookStoreResponse.setBody(BookStoreWrapper.createBookStoreResponse(bookDetailsResponse));
	 
		return bookStoreResponse;
	}
	
	@GetMapping(value = "/getbook/{searchField:isbn|title|author}/{searchValue}",  headers = "Accept=application/json")
	public ResponseDto<List<BookStoreResponseDto>> getBook(@PathVariable("searchField") String searchField,
												@PathVariable("searchValue") String searchValue) {
		
		LOGGER.info("-- Inside [BookStoreController] [Method: getUserByUserId()] with [Data:{},{}]",searchField,searchValue);
		List<BookDetails> bookDetailsResponse = bookService.getBook(searchField, searchValue);
		
		ResponseDto<List<BookStoreResponseDto>> bookStoreResponse= new ResponseDto<>();
		bookStoreResponse.setBody(BookStoreWrapper.createBookStoreResponseList(bookDetailsResponse));
	 
		return bookStoreResponse;
	}
	
	@GetMapping(value = "/mediacoverage/{searchField:isbn}/{searchValue}",  headers = "Accept=application/json")
	public ResponseDto<List<MediaCoverageResponseDto>> getMediaCoverage(@PathVariable("searchField") String searchField,
														 @PathVariable("searchValue") String searchValue) {
		
		LOGGER.info("-- Inside [BookStoreController] [Method: getMediaCoverage()] with [Data:{},{}]",searchField,searchValue);
		List<MediaCoverageResponseDto> mediaCoverageResponseList = bookService.getMediaCoverage(searchField, searchValue);
		
		ResponseDto<List<MediaCoverageResponseDto>> mediaCoverageResponse= new ResponseDto<>();
		mediaCoverageResponse.setBody(mediaCoverageResponseList);
	 
		return mediaCoverageResponse;
	}
	
}