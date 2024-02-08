package com.myblog11.myblog11.controller;

import com.myblog11.myblog11.payload.CommentDto;
import com.myblog11.myblog11.service.impl.CommentServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private CommentServiceImpl commentService;
    //create the external library bean ,here the bean will not create by Spring IOC due to not given information,
    //we must have to create the @bean annotation method of modelmapper(any external library) within @SpringBootApplication class

    public CommentController(CommentServiceImpl commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    //1.Create a comment while saving comment data to database
    // url->http://localhost:8080/api/comments?postId=3   //the basic difference between a URL and URI is that whole path is called url and
    //whole path except last Path(comments) is uri.

    public ResponseEntity<CommentDto> createComment(
            @RequestBody CommentDto commentDto,
            @RequestParam long postId
    ){
        CommentDto dtos = commentService.createComment(commentDto, postId);
        return new ResponseEntity<>(dtos, HttpStatus.CREATED);
    }

    //2.Delete comment from the database using the url
    //url-> http://localhost:8080/api/comments/CommentId , here pass the id for the comment to delete via (pathvariable/{id})
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteComment(
            @PathVariable long id
    ){
        commentService.deleteComments(id);
        return new ResponseEntity<>("Comment Is Deleted",HttpStatus.OK);
    }


    //3.Using the external library to short the code line in the program we create the UPDATE comments feature
    //url->http://localhost:8080/api/comments/update/1
     //3.Using the external librarey to short the code line in the program we create the UPDATE comments feature
    //url->http://localhost:8080/api/comments/update/1/post/3
    @PutMapping("/update/{id}/post/{postId}")
    public <postId> ResponseEntity<?> updateComment(@PathVariable long id, @RequestBody CommentDto commentDto,@PathVariable long postId
    ){
        //here BUG occur we will not only set the comment with their id,or postId if we update the comment which will not even occur
        // it will take the postId and CommentId ,and set it into the database
        CommentDto Dto = commentService.updateComment(id, commentDto,postId);
        return new ResponseEntity<>(Dto,HttpStatus.OK);
    }

}
