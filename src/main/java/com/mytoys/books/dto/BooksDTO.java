package com.mytoys.books.dto;

import java.util.List;

public class BooksDTO {
    private List<BookDTO> books;

    public BooksDTO(List<BookDTO> books){
        this.books = books;
    }
    
    public List<BookDTO> get() {
        return books;
    }
    
    public void set(List<BookDTO> books){
        this.books = books;
    }
    
    public void add(BookDTO book){
        this.books.add(book);
    }
    
    public boolean isEmpty(){
        return books.isEmpty();
    }
}
