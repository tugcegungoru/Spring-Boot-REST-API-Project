package com.example.demo.domain;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="books")
public class BookDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique=true)

    private String name;
    private String author;  //yazar
    private String publisher; //yayınevi
    private int pageNumber; //sayfa sayısı
    private int barcode;    //barkod
    private String summary;

        
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "comment_id")
    private CommentDO comment;

}
