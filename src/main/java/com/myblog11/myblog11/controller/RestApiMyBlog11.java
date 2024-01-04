package com.myblog11.myblog11.controller;

import com.myblog11.myblog11.entity.Post;
import com.myblog11.myblog11.payload.Postdto;
import com.myblog11.myblog11.service.postservice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/myblog")
public class RestApiMyBlog11 {

    //now we have to crete a class of the service layer which deal with the database,using CONSTRUCTOR way we can Inject DI without AUTOWIRED,

    private postservice postserv;

    public RestApiMyBlog11(postservice postserv) {
        this.postserv = postserv;
    }

    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody Postdto postdto){

        Postdto dto = postserv.createPost(postdto);

        return new ResponseEntity<>(dto, HttpStatus.CREATED);

    }
}
