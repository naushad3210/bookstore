package com.bookstore.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.domain.BookDetails;
import com.bookstore.dto.request.PurchaseBookRequestDto;
import com.bookstore.dto.response.BookStoreResponseDto;
import com.bookstore.dto.response.ResponseDto;
import com.bookstore.service.IPurchaseBookService;
import com.bookstore.wrapper.BookStoreWrapper;
/**
 * @author mohammadnaushad
 *
 */
@RestController
@RequestMapping("/bookstore")
public class PurchaseController {
	
private static final Logger LOGGER = LoggerFactory.getLogger(PurchaseController.class);
	
	@Autowired
	IPurchaseBookService purchaseBookService;
	
	@PostMapping(value = "/purchase",  headers = "Accept=application/json")
	public ResponseDto<BookStoreResponseDto> purchaseBook(@RequestBody @Valid PurchaseBookRequestDto purchaseBookDto) {
		LOGGER.info("-- Inside [PurchaseController] [purchaseBook()] with [Data:{}]",purchaseBookDto);
		
		BookDetails purchaseBookResponse = purchaseBookService.purchaseBook(purchaseBookDto);
		
		ResponseDto<BookStoreResponseDto> purchaseResponse= new ResponseDto<>();
		purchaseResponse.setBody(BookStoreWrapper.createBookStoreResponse(purchaseBookResponse));
	 
		return purchaseResponse;
	}
	
	

}
