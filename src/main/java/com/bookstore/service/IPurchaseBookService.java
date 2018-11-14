package com.bookstore.service;

import com.bookstore.domain.BookDetails;
import com.bookstore.dto.request.PurchaseBookRequestDto;
/**
 * @author mohammadnaushad
 *
 */
public interface IPurchaseBookService {
	
	public BookDetails purchaseBook(PurchaseBookRequestDto purchaseDto);

}
