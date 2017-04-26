package com.mytoys.books.service

import com.google.api.services.books.model.Volumes
import com.mytoys.books.dto.BooksDTO
import com.mytoys.books.persistence.repository.BookRepository

import groovy.json.JsonSlurper
import spock.lang.Specification

class BooksServiceTest extends Specification{

    BookRepository bookRepositoryMock = Mock(BookRepository);

	BooksService booksService = null
    com.google.api.services.books.Books.Volumes.List volumesList = null
    
	
    //RAW DATA
    static final TOTAL_BOOKS = 10

	final apiResult = '{"items":[{"accessInfo":{"accessViewStatus":"SAMPLE","country":"ES","embeddable":true,"epub":{"isAvailable":false},"pdf":{"isAvailable":false},"publicDomain":false,"quoteSharingAllowed":false,"textToSpeechPermission":"ALLOWED","viewability":"ALL_PAGES","webReaderLink":"http://books.google.es/books/reader?id=WYFFbVYEzvkC&hl=&printsec=frontcover&output=reader&source=gbs_api"},"etag":"kps3PIIDcFk","id":"WYFFbVYEzvkC","kind":"books#volume","saleInfo":{"country":"ES","isEbook":false,"saleability":"NOT_FOR_SALE"},"searchInfo":{"textSnippet":"4. Blacks — Brazil — History. 5. Indians of South America — Brazil — History. 6. <br>\nFamily — Brazil — History. I. Title. II. <b>Title</b>: <b>Casa</b>-grande &amp; sensala. III. <b>Title</b>: <b>Casa</b>-<br>\ngrande e senzala. F2510.F7522 1986 981 86-19197 ISBN 0-520-05665-5 (pbk."},"selfLink":"https://www.googleapis.com/books/v1/volumes/WYFFbVYEzvkC","volumeInfo":{"allowAnonLogging":false,"authors":["Gilberto Freyre"],"averageRating":5.0,"canonicalVolumeLink":"https://books.google.com/books/about/Casa_grande_E_Senzala.html?hl=&id=WYFFbVYEzvkC","categories":["History"],"contentVersion":"preview-1.0.0","imageLinks":{"smallThumbnail":"http://books.google.com/books/content?id=WYFFbVYEzvkC&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api","thumbnail":"http://books.google.com/books/content?id=WYFFbVYEzvkC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api"},"industryIdentifiers":[{"identifier":"0520056655","type":"ISBN_10"},{"identifier":"9780520056657","type":"ISBN_13"}],"infoLink":"http://books.google.es/books?id=WYFFbVYEzvkC&dq=title:+casa&hl=&source=gbs_api","language":"en","maturityRating":"NOT_MATURE","pageCount":537,"previewLink":"http://books.google.es/books?id=WYFFbVYEzvkC&pg=PR6&dq=title:+casa&hl=&cd=1&source=gbs_api","printType":"BOOK","publishedDate":"1986-01","publisher":"Univ of California Press","ratingsCount":1,"readingModes":{"text":false,"image":true},"title":"Casa-grande E Senzala"}}],"kind":"books#volumes","totalItems":1658}'
    
    def setup(){
        //booksService.bookRepository = bookRepositoryMock;
        
    }

	
	def setupApiOverride(){
		booksService = new BooksService(){
			
			@Override
			protected Volumes apiCall(String titleQuery) throws IOException{
				Volumes volumes = new JsonSlurper().parseText(apiResult)
			}
		}
	}
	

    def "When I search books on Google API I get result number expected"() {
        setup:
			setupApiOverride()

        when:
            BooksDTO response = booksService.search("house")
        then:
            response!=null
            response.get().size()==TOTAL_BOOKS
    }

    def "When API dont return any result the method should return null value"() {
        setup:
            volumesList.execute() >> new Volumes()
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
