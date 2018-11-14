package com.bookstore.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bookstore.dao.BookStoreDAO;
import com.bookstore.domain.BookDetails;
import com.bookstore.dto.request.PurchaseBookRequestDto;
import com.bookstore.exceptions.FieldMissingException;
import com.bookstore.exceptions.ResourceException;
import com.bookstore.service.IPurchaseBookService;
/**
 * @author mohammadnaushad
 *
 */
@Service("purchaseService")
public class PurchaseBookServiceImpl  implements IPurchaseBookService{

	private static final Logger LOGGER = LoggerFactory.getLogger(PurchaseBookServiceImpl.class);
	
	@Autowired
	BookStoreDAO bookStoreDao;
	
	@Override
	@Transactional(isolation=Isolation.READ_COMMITTED,propagation=Propagation.REQUIRES_NEW)
	public BookDetails purchaseBook(PurchaseBookRequestDto purchaseDto) {
		LOGGER.info("-- Inside [PurchaseBookServiceImpl] [Method: purchaseBook()] with [Data:{}]",purchaseDto);
		BookDetails book=null;
		if(purchaseDto.getIsbn()!=null) {
			book = bookStoreDao.getBookDetailsByIsbn(purchaseDto.getIsbn());
			
			if(book!=null && book.getQuantity()>0 && book.getQuantity()>purchaseDto.getQuantity()) {
				book.setQuantity(book.getQuantity() - purchaseDto.getQuantity());
			}else {
				LOGGER.info("Book Not Found !!!");
				throw new ResourceException("Book", "ISBN", purchaseDto.getIsbn());
			}
		}else {
			if((purchaseDto.getAuthor()!=null && purchaseDto.getTitle()!=null)) {
				book = bookStoreDao.getBookDetailsByAuthorAndTitle(purchaseDto);
				if(book!=null && book.getQuantity()>0 && book.getQuantity()>purchaseDto.getQuantity()) {
					book.setQuantity(book.getQuantity() - purchaseDto.getQuantity());
				}else {
					LOGGER.info("Book Not Found !!!");
					throw new ResourceException("Book", "Title by Author ", purchaseDto.getTitle()+" "+ purchaseDto.getAuthor());
				}
			}else {
				LOGGER.info("Mandatory Fields Missing !!!");
				throw new FieldMissingException("Mandatory Fields","Author, Title, ISBN",null);
			}
		}
		
		return bookStoreDao.purchaseBook(book);
	}

}
