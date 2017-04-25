package com.mytoys.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mytoys.dto.BookDTO;
import com.mytoys.service.BooksService;

@RestController
@RequestMapping(value = "/books")
public class BooksRestController {

	@Autowired
	private BooksService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> suggestBooks(@RequestParam String term){
		
		List<BookDTO> booksList = service.suggestion(term);
		
		

		return ResponseEntity.ok(booksList);
		
	}
}
