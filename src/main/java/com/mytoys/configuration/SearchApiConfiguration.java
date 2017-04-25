package com.mytoys.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.services.books.Books;
import com.google.api.services.books.BooksRequestInitializer;

public class SearchApiConfiguration {

//	@Value("#{google.books.search.api.key}")
//	private String API_KEY;
//	
//	@Bean
//	public Books apiConfiguration(){
////	    // Set up Books client.
//	    final Books books = new Books.Builder(GoogleNetHttpTransport.newTrustedTransport(), jsonFactory, null)
//	        .setApplicationName(APPLICATION_NAME)
//	        .setGoogleClientRequestInitializer(new BooksRequestInitializer(API_KEY))
//	        .build();
//	    return books;
//	}
}
