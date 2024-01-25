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
    //url->http://localhost:8080/api/comments?postId=3
    public ResponseEntity<CommentDto> createComment(
            @RequestBody CommentDto commentDto,
            @RequestParam long postId
    ){
        CommentDto dtos = commentService.createComment(commentDto, postId);

        return new ResponseEntity<>(dtos, HttpStatus.CREATED);
    }
}
