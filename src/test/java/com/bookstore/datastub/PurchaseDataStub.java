package com.bookstore.datastub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.bookstore.domain.BookDetails;
import com.bookstore.dto.request.PurchaseBookRequestDto;
import com.bookstore.dto.response.BookStoreResponseDto;
import com.bookstore.dto.response.ResponseDto;
/**
 * @author mohammadnaushad
 *
 */
@Component
public class PurchaseDataStub {
	
	@Autowired
	Environment env;
	
	public PurchaseBookRequestDto purchaseBookDto() {
		PurchaseBookRequestDto dto = new PurchaseBookRequestDto();
		dto.setAuthor(env.getProperty("purchase_author"));
		dto.setIsbn(env.getProperty("purchase_isbn"));
		dto.setQuantity(Integer.parseInt(env.getProperty("purchase_quantity")));
		dto.setTitle(env.getProperty("purchase_title"));
		return dto;
	}
	
	public  ResponseDto<BookStoreResponseDto> purchaseResponse(){
		ResponseDto<BookStoreResponseDto> bookStoreResponseDto= new ResponseDto<>();
		bookStoreResponseDto.setBody(purchaseResponseDto());
		return bookStoreResponseDto;
	}
	
	public BookStoreResponseDto purchaseResponseDto() {
		BookStoreResponseDto bookDetails = new BookStoreResponseDto();
		bookDetails.setIsbn(env.getProperty("purchase_isbn"));
		bookDetails.setAuthor(env.getProperty("purchase_author"));
		bookDetails.setTitle(env.getProperty("purchase_title"));
		bookDetails.setId(new Long(env.getProperty("purchase_id")));
		bookDetails.setQuantity(Integer.parseInt(env.getProperty("after_purchase_quantity")));
		return bookDetails;
	}

	public BookDetails purchaseBookDetails() {
		BookDetails bookDetails = new BookDetails();
		bookDetails.setIsbn(env.getProperty("db_isbn"));
		bookDetails.setAuthor(env.getProperty("db_author"));
		bookDetails.setTitle(env.getProperty("db_title"));
		bookDetails.setId(new Long(env.getProperty("db_id")));
		bookDetails.setQuantity(Integer.parseInt(env.getProperty("after_purchase_quantity")));
		return bookDetails;
	}
}

