package com.mytoys.books.api

import static org.springframework.http.HttpStatus.*
import static org.springframework.http.MediaType.APPLICATION_JSON
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup

import java.util.List

import org.springframework.test.web.servlet.MockMvc

import com.google.api.services.books.Books.Cloudloading.AddBook
import com.mytoys.books.dto.BookDTO
import com.mytoys.books.dto.BooksDTO
import com.mytoys.books.service.BooksService

import groovy.json.JsonSlurper
import spock.lang.Specification

class BooksRestControllerTest extends Specification{

    BooksService bookServiceMock = Mock(BooksService);
    BooksDTO booksDTO = null
    BooksRestController bookRestController = new BooksRestController();
    MockMvc mockMvc = standaloneSetup(bookRestController).build()
    
    def setup(){
        bookRestController.service = bookServiceMock;
        BookDTO book = new BookDTO("dkej3jdkd","Red House", "http://test.com")
        List<BookDTO> books = new ArrayList<BookDTO>()
        books.add(book)
        booksDTO = new BooksDTO(books);
    }
    
    def "When I introduce a term to search using the API I get expected results"() {
        setup: 'setting response'
            bookServiceMock.searchAndStore(_) >> booksDTO
        when: 'calling api'
            def response = mockMvc.perform(get('/books?term=house')).andReturn().response
            def content = new JsonSlurper().parseText(response.contentAsString)
        then: 'response status is equal to 200, encoding is correct and result expected'
            response.status == OK.value()
            response.contentType.contains(APPLICATION_JSON.toString())
            response.characterEncoding=='UTF-8'
            content.get(0).id=="dkej3jdkd";
    }

 
    def "If I dont add a term to the url I get bad request response because is required"() {
        when: 'calling api'
            def response = mockMvc.perform(get('/books')).andReturn().response
        then: 'response status is equal to 400, encoding is correct'
            response.status == BAD_REQUEST.value()
    }
 
    def "When an unpredictable exception happen on the service the response code is 500"() {
        setup: 'setting exception'
            bookServiceMock.searchAndStore(_) >> { throw new Exception() }
        when: 'calling api'
            def response = mockMvc.perform(get('/books?term=house')).andReturn().response
        then: 'response status is equal to 500, encoding is correct'
            response.status == INTERNAL_SERVER_ERROR.value()
    }
}
