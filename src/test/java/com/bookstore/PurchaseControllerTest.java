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

import com.bookstore.controller.PurchaseController;
import com.bookstore.datastub.PurchaseDataStub;
import com.bookstore.dto.request.PurchaseBookRequestDto;
import com.bookstore.service.IPurchaseBookService;

/**
 * @author mohammadnaushad
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PurchaseControllerTest extends BookStoreApplicationTest{
	private static final Logger LOGGER = LoggerFactory.getLogger(PurchaseControllerTest.class);
	
	@Mock
	IPurchaseBookService purchaseBookServiceMock;

	@InjectMocks
	PurchaseController purchaseControllerMock;
	
	@Autowired
	PurchaseDataStub purchaseDataStub;
	
	@Autowired
	Environment env;
	
	@Test
	public void purchaseBookTest() {
		LOGGER.info("-- Testing [PurchaseControllerTest] [Method: purchaseBookTest()]");
		when(purchaseBookServiceMock.purchaseBook(Mockito.any(PurchaseBookRequestDto.class))).thenReturn(purchaseDataStub.purchaseBookDetails());
		assertEquals(purchaseDataStub.purchaseResponse(),purchaseControllerMock.purchaseBook(purchaseDataStub.purchaseBookDto()));
    }
}
