package com.myblog11.myblog11.controller;

import com.myblog11.myblog11.payload.CommentDto;
import com.myblog11.myblog11.service.impl.CommentServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private CommentServiceImpl commentService;

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
    //url-> http://localhost:8080/api/comments , here pass the id for the comment to delete via (pathvariable/{id})
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteComment(
            @PathVariable long id
    ){
        commentService.deleteComments(id);
        return new ResponseEntity<>("Comment Is Deleted",HttpStatus.OK);
    }
}
