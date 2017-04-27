package com.mytoys.books.service

import com.google.api.services.books.model.Volume
import com.google.api.services.books.model.Volumes
import com.google.api.services.books.model.Volume.VolumeInfo
import com.mytoys.books.dto.BooksDTO
import com.mytoys.books.exception.BooksNotFoundException
import com.mytoys.books.persistence.repository.BookRepository

import spock.lang.Specification

class BooksServiceTest extends Specification{

    BookRepository bookRepositoryMock = Mock(BookRepository);

	BooksService booksService = null
    com.google.api.services.books.Books.Volumes.List volumesList = null
    
	
    //RAW DATA
    static final TOTAL_BOOKS = 1
    static final EMPTY_BOOKS = 0
    Volumes volumes = new Volumes();
    

    def setupRepository(){
        BooksService booksService = new BooksService()
        booksService.bookRepository = bookRepositoryMock;
        
    }

    def setupVolumes(){
        List<Volume> items = new ArrayList<Volume>();
        Volume volume = new Volume();
        volume.setId("codTest");
        volume.setVolumeInfo(new VolumeInfo());
        volume.getVolumeInfo().setTitle("titleTest");
        volume.setSelfLink("http://test")
        items.add(volume);
        volumes.setItems(items)
    }
	
	def setupApiOverride(){
		booksService = new BooksService(){
			
			@Override
			protected Volumes apiCall(String titleQuery) throws IOException{
                volumes
			}
		}
	}
	

    def "When I search books on Google API I get result number expected and content expected"() {
        setup:
            setupVolumes()
			setupApiOverride()

        when:
            BooksDTO response = booksService.search("anyValue")
        then:
            response!=null
            response.get().size()==TOTAL_BOOKS
            response.get().get(0).getId()=="codTest"
    }

    def "When API dont return any result the method should return empty array"() {
        setup:
            setupApiOverride()
        when:
            BooksDTO response = booksService.search("anyValue")
        then:
            thrown(BooksNotFoundException)

    }

    def "When some error happen storing the data execution keeps working and user shouldn't notice"() {
        setup:
            setupRepository()
            bookRepositoryMock.save(_) >> { throw new Exception() }
        when:
            booksService.store(new BooksDTO());
        then:
            thrown(Exception)

    }
}
