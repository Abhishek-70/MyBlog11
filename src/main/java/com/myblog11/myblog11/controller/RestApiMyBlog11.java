package com.myblog11.myblog11.controller;

import com.myblog11.myblog11.entity.Post;
import com.myblog11.myblog11.payload.Postdto;
import com.myblog11.myblog11.service.postservice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    //for Accessing this data we Have To Use url In postman (http.//localhost:8080/api/myblog?id=12
    //Here We handle the Exception Occured while Accessing through invalid id number
    @GetMapping
    public ResponseEntity<?> getById(@RequestParam long id){

        Postdto dtoFounded = postserv.getPostById(id);
        return new ResponseEntity<>(dtoFounded,HttpStatus.INTERNAL_SERVER_ERROR);
            }

//now we Get The Data From the Database Using The Technique Stream Api
    //url-> http://localhost:8080/api/myblog/getAll
    @GetMapping("/getAll")
   public List<Postdto> getAllData(){
        List<Postdto> fdtos = postserv.getAllData();
        return fdtos;
    }

}
