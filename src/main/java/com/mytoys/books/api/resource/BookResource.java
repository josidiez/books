package com.mytoys.books.api.resource;

import io.swagger.annotations.ApiModelProperty;

public class BookResource {

    @ApiModelProperty(value = "Public Book Google ID")
    private String id;
    @ApiModelProperty(value = "Title")
    private String title;
    @ApiModelProperty(value = "Link to book store")
    private String link;
    
    
    
    
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
