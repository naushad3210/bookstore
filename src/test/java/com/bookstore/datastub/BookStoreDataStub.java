package com.bookstore.datastub;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.bookstore.domain.BookDetails;
import com.bookstore.dto.request.BookStoreRequestDto;
import com.bookstore.dto.response.BookStoreResponseDto;
import com.bookstore.dto.response.MediaCoverageResponseDto;
import com.bookstore.dto.response.ResponseDto;
/**
 * @author mohammadnaushad
 *
 */
@Component
public class BookStoreDataStub {

	@Autowired
	Environment env;
	
	public BookDetails bookDetails() {
		BookDetails bookDetails = new BookDetails();
		bookDetails.setIsbn(env.getProperty("db_isbn"));
		bookDetails.setAuthor(env.getProperty("db_author"));
		bookDetails.setTitle(env.getProperty("db_title"));
		bookDetails.setPrice(Double.parseDouble(env.getProperty("db_price")));
		bookDetails.setId(new Long(env.getProperty("db_id")));
		bookDetails.setQuantity(Integer.parseInt(env.getProperty("db_add_quantity")));
		return bookDetails;
	}
	
	public List<BookDetails> bookDetailsList(){
		List<BookDetails> list = new ArrayList<>();
		BookDetails bookDetails = new BookDetails();
		bookDetails.setIsbn(env.getProperty("db_isbn"));
		bookDetails.setAuthor(env.getProperty("db_author"));
		bookDetails.setTitle(env.getProperty("db_title"));
		bookDetails.setPrice(Double.parseDouble(env.getProperty("db_price")));
		bookDetails.setId(new Long(env.getProperty("db_id")));
		bookDetails.setQuantity(Integer.parseInt(env.getProperty("db_add_quantity")));
		list.add(bookDetails);
		return list;
	}
	
	public BookStoreRequestDto bookStoreRequestDto() {
		BookStoreRequestDto bookStoreReqDto = new BookStoreRequestDto(); 
		bookStoreReqDto.setIsbn(env.getProperty("req_isbn"));
		bookStoreReqDto.setAuthor(env.getProperty("req_author"));
		bookStoreReqDto.setTitle(env.getProperty("req_title"));
		bookStoreReqDto.setPrice(Double.parseDouble(env.getProperty("req_price")));
		bookStoreReqDto.setQuantity(Integer.parseInt(env.getProperty("req_add_quantity")));
		return bookStoreReqDto;
	}
	
	public BookStoreResponseDto bookStoreResponseDto() {
		BookStoreResponseDto bookDetails = new BookStoreResponseDto();
		bookDetails.setIsbn(env.getProperty("resp_isbn"));
		bookDetails.setAuthor(env.getProperty("resp_author"));
		bookDetails.setTitle(env.getProperty("resp_title"));
		bookDetails.setPrice(Double.parseDouble(env.getProperty("resp_price")));
		bookDetails.setId(new Long(env.getProperty("resp_id")));
		bookDetails.setQuantity(Integer.parseInt(env.getProperty("resp_add_quantity")));
		return bookDetails;
	}
	
	public  ResponseDto<BookStoreResponseDto> bookStoreResponse(){
		ResponseDto<BookStoreResponseDto> bookStoreResponseDto= new ResponseDto<>();
		bookStoreResponseDto.setBody(bookStoreResponseDto());
		return bookStoreResponseDto;
	}

	public  ResponseDto<List<BookStoreResponseDto>> bookStoreResponseList(){
		ResponseDto<List<BookStoreResponseDto>> bookStoreResponseDto= new ResponseDto<>();
		List<BookStoreResponseDto> list = new ArrayList<>();
		list.add(bookStoreResponseDto());
		bookStoreResponseDto.setBody(list);
		return bookStoreResponseDto;
	}
	
	public  ResponseDto<List<MediaCoverageResponseDto>> mediaCoverageResponseList(){
		ResponseDto<List<MediaCoverageResponseDto>> bookStoreResponseDto= new ResponseDto<>();
		List<MediaCoverageResponseDto> list = new ArrayList<>();
		MediaCoverageResponseDto dto = new MediaCoverageResponseDto();
		dto.setTitle(env.getProperty("media_coverage_title"));
		list.add(dto);
		bookStoreResponseDto.setBody(list);
		return bookStoreResponseDto;
	}
	public List<MediaCoverageResponseDto> mediaCoverageList(){
		List<MediaCoverageResponseDto> list = new ArrayList<>();
		MediaCoverageResponseDto dto = new MediaCoverageResponseDto();
		dto.setTitle(env.getProperty("media_coverage_title"));
		list.add(dto);
		return list;
	}
	
}
