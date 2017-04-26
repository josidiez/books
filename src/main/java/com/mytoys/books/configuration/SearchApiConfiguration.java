package com.mytoys.books.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.books.Books;
import com.google.api.services.books.BooksRequestInitializer;

@Configuration
@PropertySource("classpath:/api.properties")
public class SearchApiConfiguration {

    @Value("${google.books.search.api.name.app}")
    public String APPLICATION_NAME;

    @Value("${google.books.search.api.key}")
    private String API_KEY;

	
	@Bean
	public Books booksApi() throws Exception{
	    
        final Books books = new Books.Builder(GoogleNetHttpTransport.newTrustedTransport(), new JacksonFactory(), null)
                .setApplicationName(APPLICATION_NAME)
                .setGoogleClientRequestInitializer(new BooksRequestInitializer(API_KEY))
                .build();
        return books;
	}
}
