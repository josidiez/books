package com.mytoys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.services.books.Books;
import com.google.api.services.books.BooksRequestInitializer;
import com.mytoys.dto.BookDTO;

public class BooksService {



	
	private static final String APPLICATION_NAME = "sample-app";

	private String API_KEY="";
	
	public List<BookDTO> suggestion(String term){
		
		
//	    // Set up Books client.
	    final Books books = new Books.Builder(GoogleNetHttpTransport.newTrustedTransport(), jsonFactory, null)
	        .setApplicationName(APPLICATION_NAME)
	        .setGoogleClientRequestInitializer(new BooksRequestInitializer(API_KEY))
	        .build();
	    
//	    // Set query string and filter only Google eBooks.
//	    System.out.println("Query: [" + query + "]");
//	    List volumesList = books.volumes().list(query);
//	    volumesList.setFilter("ebooks");
//
//	    // Execute the query.
//	    Volumes volumes = volumesList.execute();
		
		return null;
	}
}
