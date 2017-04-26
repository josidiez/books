package com.mytoys.books.service

import com.google.api.services.books.Books;
import com.google.api.services.books.Books.Bookshelves.Volumes
import com.mytoys.books.dto.BooksDTO
import com.mytoys.books.persistence.repository.BookRepository

import spock.lang.Specification

class BookServiceTest extends Specification{

    BookRepository bookRepositoryMock = Mock(BookRepository);

    Books booksApiMock = Mock(Books)
    
    BooksService booksService = new BooksService();
    com.google.api.services.books.Books.Volumes.List volumesList = null
    
    //RAW DATA
    static final TOTAL_BOOKS = 10

    
    
    def setup(){
        booksService.bookRepository = bookRepositoryMock;
        volumesList = bookRepositoryMock.volumes().list(_);
    }


    def "When I search books on Google API I get the result expected"() {
        setup:
            volumesList.execute() >> new Volumes()
        when:
            BooksDTO response = booksService.searchAndStore("house")
        then:
            response!=null
            response.get().size()==TOTAL_BOOKS
    }

    def "When API dont return any result the method should return null value"() {
        setup:
            volumesList.execute() >> townsProvince
        when:
            BooksDTO response = booksService.search(_)
        then:
            response==null

    }

    def "When some error happen storing the data execution keeps working and user shouldn't notice"() {
        setup:
            bookRepositoryMock.save(_) >> { throw new Exception() }
        when:
            booksService.store(new BooksDTO());
        then:
            thrown(Exception)

    }
}
