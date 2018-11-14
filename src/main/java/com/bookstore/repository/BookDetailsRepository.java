package com.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookstore.domain.BookDetails;


/**
 * @author mohammadnaushad
 *
 */
@Repository
public interface BookDetailsRepository  extends JpaRepository<BookDetails, Long>{
	
	BookDetails findByIsbn(String isbn);
	BookDetails findByAuthorAndTitle(String author, String title);
	
}
