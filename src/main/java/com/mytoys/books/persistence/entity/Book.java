package com.mytoys.books.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.mytoys.books.dto.BookDTO;

@Entity
@Table(name = "BOOKS")
public class Book {
    
    @Id
    private String id;
    private String title;
    private String link;
    
    public Book(){}
    
    public Book(BookDTO bookDTO){
        this.id = bookDTO.getId();
        this.title = bookDTO.getTitle();
        this.link = bookDTO.getLink();
    }
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getLink() {
        return link;
    }
    public void setLink(String link) {
        this.link = link;
    }
}
