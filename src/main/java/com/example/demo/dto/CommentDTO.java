package com.example.demo.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class CommentDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private String title;
    private String message;
}
