package com.myblog11.myblog11.service.impl;

import com.myblog11.myblog11.entity.Comment;
import com.myblog11.myblog11.entity.Post;
import com.myblog11.myblog11.exception.ResourceNotFoundException;
import com.myblog11.myblog11.payload.CommentDto;
import com.myblog11.myblog11.repository.CommentRepository;
import com.myblog11.myblog11.repository.postRepository;
import com.myblog11.myblog11.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
    public class CommentServiceImpl implements CommentService{

    private postRepository postrepo;
    private CommentRepository commentRepository;
    public CommentServiceImpl(CommentRepository commentRepository,postRepository postrepo) {
        this.commentRepository = commentRepository;
        this.postrepo = postrepo;

    }

      @Override
    public CommentDto createComment(CommentDto commentDto, long postId) {

        Post post = postrepo.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("This Post is Not Found With This Id:" + postId)
        );
        Comment comment = new Comment();
        comment.setText(commentDto.getText());
        comment.setEmail(commentDto.getEmail());
        comment.setPost(post);

          Comment saveComment = commentRepository.save(comment);

          //This CommentDto Is Specific CommentDto Which we set To The ResponseEntity To Show the content On the Console.
        //If we Like Use another Dto Class which have some Different parameter than that can also used
        CommentDto dto = new CommentDto();
        dto.setId(saveComment.getId());
        dto.setText(saveComment.getText());
        dto.setEmail(saveComment.getEmail());
        return dto;
    }

    @Override
    public void deleteComments(long id) {
       commentRepository.deleteById(id);
          }
}


