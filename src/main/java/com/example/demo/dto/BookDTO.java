package com.example.demo.dto;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class BookDTO implements Serializable{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    
    @JsonIgnore
    private String name;    //kitapadı
    private String author;  //yazar
    private String publisher; //yayınevi
    private int pageNumber; //sayfa sayısı
    private int barcode;    //barkod
    private String summary; //özet
    @JsonProperty("comment")
    private CommentDTO comment;
}
