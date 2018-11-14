package com.bookstore;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

import com.bookstore.controller.BookStoreController;
import com.bookstore.datastub.BookStoreDataStub;
import com.bookstore.dto.request.BookStoreRequestDto;
import com.bookstore.service.IBookStoreService;

/**
 * @author mohammadnaushad
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BookStoreControllerTest  extends BookStoreApplicationTest{
	private static final Logger LOGGER = LoggerFactory.getLogger(BookStoreControllerTest.class);
	
	@Mock
	IBookStoreService bookStoreServiceMock;

	@InjectMocks
	BookStoreController bookStoreControllerMock;
	
	@Autowired
	BookStoreDataStub bookStoreDataStub;
	
	@Autowired
	Environment env;
	
	@Test
	public void addBookTest() {
		LOGGER.info("-- Testing [BillControllerTest] [Method: addBookTest()]");
		when(bookStoreServiceMock.addBook(Mockito.any(BookStoreRequestDto.class))).thenReturn(bookStoreDataStub.bookDetails());
		assertEquals(bookStoreDataStub.bookStoreResponse(),bookStoreControllerMock.addBook(bookStoreDataStub.bookStoreRequestDto()));
    }
	
	@Test
	public void getBookTestIsbn() {
		LOGGER.info("-- Testing [BillControllerTest] [Method: getBookTestIsbn()]");
		when(bookStoreServiceMock.getBook(Mockito.any(String.class), Mockito.any(String.class))).thenReturn(bookStoreDataStub.bookDetailsList());
		assertEquals(bookStoreDataStub.bookStoreResponseList(),bookStoreControllerMock.getBook("isbn",env.getProperty("get_book_isbn")));
    }
	
	@Test
	public void getBookTestTitle() {
		LOGGER.info("-- Testing [BillControllerTest] [Method: getBookTestTitle()]");
		when(bookStoreServiceMock.getBook(Mockito.any(String.class), Mockito.any(String.class))).thenReturn(bookStoreDataStub.bookDetailsList());
		assertEquals(bookStoreDataStub.bookStoreResponseList(),bookStoreControllerMock.getBook("title",env.getProperty("get_book_title")));
    }
	
	@Test
	public void getBookTestAuthor() {
		LOGGER.info("-- Testing [BillControllerTest] [Method: getBookTestAuthor()]");
		when(bookStoreServiceMock.getBook(Mockito.any(String.class), Mockito.any(String.class))).thenReturn(bookStoreDataStub.bookDetailsList());
		assertEquals(bookStoreDataStub.bookStoreResponseList(),bookStoreControllerMock.getBook("author",env.getProperty("get_book_author")));
    }
	
	@Test
	public void getMediaCoverageTest() {
		LOGGER.info("-- Testing [BillControllerTest] [Method: getMediaCoverageTest()]");
		when(bookStoreServiceMock.getMediaCoverage(Mockito.any(String.class), Mockito.any(String.class))).thenReturn(bookStoreDataStub.mediaCoverageList());
		assertEquals(bookStoreDataStub.mediaCoverageResponseList(),bookStoreControllerMock.getMediaCoverage("isbn",env.getProperty("get_book_isbn")));
    }
}
