package com.bookstore.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bookstore.domain.BookDetails;
import com.bookstore.dto.request.PurchaseBookRequestDto;
import com.bookstore.exceptions.DuplicateRecordException;
import com.bookstore.repository.BookDetailsRepository;
/**
 * @author mohammadnaushad
 *
 */
@Service
@Transactional(rollbackFor=Exception.class,isolation= Isolation.READ_COMMITTED)
public class BookStoreDAO {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BookStoreDAO.class);
	
	@Autowired
	private BookDetailsRepository bookDetailsRepository;
	
	public BookDetails persistBookStore(BookDetails bookDetails) {
		LOGGER.info("-- Inside [BookStoreDAO] [persistBookStore()] with [Data:{}]",bookDetails);
		
		if(bookDetailsRepository.findByIsbn(bookDetails.getIsbn())!=null) {
			LOGGER.info("Duplicate Record !!!");
			throw new DuplicateRecordException("Book","ISBN",bookDetails.getIsbn());
		}else if(bookDetailsRepository.findByAuthorAndTitle(bookDetails.getAuthor(), bookDetails.getTitle())!=null){
			LOGGER.info("Duplicate Record !!!");
			throw new DuplicateRecordException("Book","Title",bookDetails.getTitle()+" by Author "+bookDetails.getAuthor());
		}
		else {
			return bookDetailsRepository.save(bookDetails);
		}
	}
	
	public List<BookDetails> getBookDetails() {
		LOGGER.info("-- Inside [BookStoreDAO] [getBookDetails()]");
		return bookDetailsRepository.findAll();
	}
	
	public BookDetails getBookDetailsByIsbn(String isbn) {
		LOGGER.info("-- Inside [BookStoreDAO] [getBookDetailsByIsbn()] with [isbn:{}]",isbn);
		return bookDetailsRepository.findByIsbn(isbn);
	}
	
	public BookDetails getBookDetailsByAuthorAndTitle(PurchaseBookRequestDto dto) {
		LOGGER.info("-- Inside [BookStoreDAO] [getBookDetailsByAuthorAndTitle()] with [Data:{}]",dto);
		return bookDetailsRepository.findByAuthorAndTitle(dto.getAuthor(),dto.getTitle());
	}
	
	@Transactional(propagation=Propagation.MANDATORY)
	public BookDetails purchaseBook(BookDetails book) {
		LOGGER.info("-- Inside [BookStoreDAO] [purchaseBook()] with [Data:{}]",book);
		return bookDetailsRepository.save(book);
	}

}
