package com.mytoys.books.exception;


public class BooksNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -8434374021523470873L;

    public BooksNotFoundException(String message) {
        super(message);
    }

}
