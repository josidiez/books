package com.mytoys.books.api;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mytoys.books.api.resource.BookResource;
import com.mytoys.books.dto.BookDTO;
import com.mytoys.books.dto.BooksDTO;
import com.mytoys.books.exception.BooksNotFoundException;
import com.mytoys.books.service.BooksService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping(value = "/books")
public class BooksRestController {

	@Autowired
	private BooksService service;
	
    @ApiOperation(value = "Search books by title", nickname = "titleSearch")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "term", value = "Term used to search directly on google's api", required = true, dataType = "string", paramType = "query") })
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = BookResource.class, responseContainer="List"),
                           @ApiResponse(code = 400, message = "Bad Request"),
                           @ApiResponse(code = 500, message = "Internal Error")})
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> suggestBooks(@RequestParam(required=true) String term) throws BooksNotFoundException,Exception{
		
		BooksDTO books = service.searchAndStore(term);
		
		List<BookResource> resources = books.get().stream()
                    .map(v -> new BookResource(v.getId(),v.getTitle(),v.getLink()))
                    .collect(Collectors.toList());
		
		return ResponseEntity.ok(resources);
	}


    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public ResponseEntity<?> handlerMissingParameter(MissingServletRequestParameterException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<?> handlerNotControlledException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    
    @ExceptionHandler(value = BooksNotFoundException.class)
    public ResponseEntity<?> handlerNotFoundException(BooksNotFoundException e) {
        return ResponseEntity.notFound().build();
    }
}
