package com.myblog11.myblog11.controller;

import com.myblog11.myblog11.payload.Postdto;
import com.myblog11.myblog11.service.postservice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blog")
public class RestApiMyBlog11 {

    //now we have to crete a class of the service layer which deal with the database,using CONSTRUCTOR way we can Inject DI without AUTOWIRED,

    private postservice postserv;

    public RestApiMyBlog11(postservice postserv) {
        this.postserv = postserv;
    }

    //url-> http://localhost:8080/api/blog
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

    //1.here  we show the concept of pagenation with pageNo and pageSize only
    //url->http://localhost:8080/api/blog?pageNo=0&pageSize=3

//    @GetMapping
//    public List<Postdto> getAllData(
//            @RequestParam(name="pageNo",required = false,defaultValue="0")int pageNo,
//            @RequestParam(name="pageSize",required = false,defaultValue = "3")int pageSize
//    ){
//        List<Postdto> allInPaginationFormat = postserv.getAllInPaginationFormat(pageNo, pageSize);
//        return allInPaginationFormat;
//    }


    //2.Here we create the pagination concept with extends sortBy feature of sorting.
    //url-> http://localhost:8080/api/blog/Pagination?pageNo=0&pageSize=4&sortBy=title
//    @GetMapping("/Pagination")
//    public List<Postdto> getAllData(
//            @RequestParam(name="pageNo",required = false,defaultValue="0")int pageNo,
//            @RequestParam(name="pageSize",required = false,defaultValue = "10")int pageSize,
//            @RequestParam(name = "sortBy",required = false,defaultValue = "id")String sortBy
//    ){
//        List<Postdto> allInPaginationFormat = postserv.getAllInPaginationFormat(pageNo, pageSize, sortBy);
//        return allInPaginationFormat;
//    }

    //3.Here crete a new feature of pagination with sortingDirection(sortDir),
    //url->http://localhost:8080/api/blog/Pagination?pageNo=0&pageSize=10&sortBy=title&sortDir=desc
    @GetMapping("/Pagination")
    public List<Postdto> getAllData(
            @RequestParam(name="pageNo",required=false,defaultValue="0")int pageNo,
            @RequestParam(name="pageSize",required = false,defaultValue = "3")int pageSize,
            @RequestParam(name="sortBy",required = false,defaultValue ="id")String sortBy,
            @RequestParam(name="sortDir",required = false,defaultValue = "id")String sortDir
    ){

        List<Postdto> allInPaginationFormat2 = postserv.getAllInPaginationFormat2(pageNo, pageSize, sortBy, sortDir);
        return allInPaginationFormat2;
    }

    //#.Delete a post from the database obtain by this operation using url
    //url->http://localhost:8080/api/blog/4
@DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(
            @PathVariable long id
    ){
        postserv.deltePostById(id);
        return new ResponseEntity<>("post is Deleted",HttpStatus.OK);
    }

}
