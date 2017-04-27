package com.mytoys.books.service;

import java.io.IOException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.google.api.services.books.Books;
import com.google.api.services.books.model.Volumes;
import com.mytoys.books.dto.BookDTO;
import com.mytoys.books.dto.BooksDTO;
import com.mytoys.books.exception.BooksNotFoundException;
import com.mytoys.books.persistence.entity.Book;
import com.mytoys.books.persistence.repository.BookRepository;

@Service
public class BooksService {
	
    private final Long MAX_RESULTS_DEMO = 10L;
    private final Integer EMPTY_RESULT = 0;
     
    @Autowired
    private Books booksApi;
    
    @Autowired
    private BookRepository bookRepository;
    
	public BooksDTO searchAndStore(String term) throws Exception{
	    BooksDTO response = this.search(term);
	    this.store(response);
	    return response;
	}
	
	public BooksDTO search(String term) throws BooksNotFoundException,Exception{

  	    final String titleQuery = queryBuilder(term);
	    
  	    Volumes volumes = this.apiCall(titleQuery);
  	    
        if (emptyResult(volumes)) {
            throw new BooksNotFoundException("No matching books");
        }

        java.util.List<BookDTO> bookList = volumes.getItems().stream()
                .map(v -> new BookDTO(v.getId(),v.getVolumeInfo().getTitle(),v.getSelfLink()))
                .collect(Collectors.toList());
    
		return new BooksDTO(bookList);
	}
	
	public void store(BooksDTO books) throws DataAccessException{
	    if (books.isEmpty()) return ;
	    
	    java.util.List<Book> entities = books.get().stream()
                .map(b -> new Book(b))
                .collect(Collectors.toList());	            
	            
	    bookRepository.save(entities) ;
	}
	
	protected Volumes apiCall(String titleQuery) throws IOException{
		return booksApi.volumes().list(titleQuery)
					.setMaxResults(MAX_RESULTS_DEMO)
					.execute();
	}
	
	private String queryBuilder(String term){
	    return String.format("title: %s", term);
	}
	
	private boolean emptyResult(Volumes volumes){
	    return volumes.getTotalItems() == EMPTY_RESULT || volumes.getItems()==null;
	}
}
