package com.bookstore.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.bookstore.dao.BookStoreDAO;
import com.bookstore.domain.BookDetails;
import com.bookstore.dto.request.BookStoreRequestDto;
import com.bookstore.dto.response.MediaCoverageResponseDto;
import com.bookstore.exceptions.ThirdPartyApiException;
import com.bookstore.exceptions.RecordNotFoundException;
import com.bookstore.service.IBookStoreService;

/**
 * @author mohammadnaushad
 *
 */
@Service("bookStoreService")
public class BookStoreServiceImpl implements IBookStoreService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BookStoreServiceImpl.class);
	
	@Autowired
	BookStoreDAO bookStoreDao;
	@Autowired
	RestTemplate restTemplate;
	
	@Override
	@Transactional
	public BookDetails addBook(BookStoreRequestDto bookStoreDto) {
		LOGGER.info("-- Inside [BookStoreServiceImpl] [Method: addBook()] with [Data:{}]",bookStoreDto);
		BookDetails bookDetails = new BookDetails();
		BeanUtils.copyProperties(bookStoreDto, bookDetails);
		
		return bookStoreDao.persistBookStore(bookDetails);
	}
	
	@Override
	@Transactional
	public List<BookDetails> getBook(String searchField, String searchValue) {
		LOGGER.info("-- Inside [BookStoreServiceImpl] [Method: getBook()] with [Data:{} = {} ",searchField,searchValue);
		List<BookDetails> bookDetailsList = bookStoreDao.getBookDetails();
		List<BookDetails>  bookDetails = new ArrayList<>();
		
		if(searchField.equalsIgnoreCase("author")) {
			bookDetails	= bookDetailsList.stream()
											.filter(book -> book.getAuthor().toUpperCase()
											.contains(searchValue.toUpperCase()))
											.collect(Collectors.toList());
		}
		else if(searchField.equalsIgnoreCase("title")) {
			bookDetails	= bookDetailsList.stream()
											.filter(book -> book.getTitle().toUpperCase()
											.contains(searchValue.toUpperCase()))
											.collect(Collectors.toList());
		}else if(searchField.equalsIgnoreCase("isbn")) {
			bookDetails	= bookDetailsList.stream()
											.filter(book -> book.getIsbn().toUpperCase()
											.equalsIgnoreCase(searchValue.toUpperCase()))
											.collect(Collectors.toList());
		}
		
		if(bookDetails.isEmpty()) {
			LOGGER.info("Book details not present with {} = {}",searchField,searchValue);
			throw new RecordNotFoundException("Book",searchField,searchValue);
		}
		
		return bookDetails;
	}

	@Override
	@Transactional
	public List<MediaCoverageResponseDto> getMediaCoverage(String searchField, String searchValue) {
		LOGGER.info("-- Inside [BookStoreServiceImpl] [Method: getBook()] with [Data:{} = {} ",searchField,searchValue);
		
		BookDetails bookDetails = bookStoreDao.getBookDetailsByIsbn(searchValue);
		
		
		if(bookDetails!=null) {
			return getMediaCoverageFromApi(bookDetails,searchField,searchValue);
		}else {
			LOGGER.info("Book details not present with {} = {}",searchField,searchValue);
			throw new RecordNotFoundException("Book",searchField,searchValue);
		}
        
	}
	
	private List<MediaCoverageResponseDto> getMediaCoverageFromApi(BookDetails bookDetails,String searchField, String searchValue) {
		List<MediaCoverageResponseDto> mediaCovResponse = new ArrayList<>();
        
		ResponseEntity<List<MediaCoverageResponseDto>> response = restTemplate.exchange("https://jsonplaceholder.typicode.com/posts", 
        													HttpMethod.GET, null, new ParameterizedTypeReference<List<MediaCoverageResponseDto>>() {});
        
        HttpStatus statusCode = response.getStatusCode();
        LOGGER.info("Response Satus Code: {}", statusCode);
 
        if (statusCode == HttpStatus.OK) {
        	List<MediaCoverageResponseDto> list = response.getBody();
 
            if (!list.isEmpty()) {
            	list.stream().filter(f->f.getTitle().toUpperCase().contains(bookDetails.getTitle().toUpperCase()) || f.getBody().toUpperCase().contains(bookDetails.getTitle().toUpperCase()))
            			.forEach(media->{
            				MediaCoverageResponseDto dto = new MediaCoverageResponseDto();
            				dto.setTitle(media.getTitle());
            				mediaCovResponse.add(dto);
            			});
            }
        }else {
        	LOGGER.info("Media Coverage Api Issue");
        	throw new ThirdPartyApiException("Media Coverage Api ", " [ http-code : " , statusCode+"]");
        }
		
        	if(mediaCovResponse.isEmpty()) {
        		LOGGER.info("Media Coverage Not Found");
        		throw new RecordNotFoundException("Media Coverage",searchField,searchValue);
        	}
        	
       	return mediaCovResponse;
		
	}
	

}
