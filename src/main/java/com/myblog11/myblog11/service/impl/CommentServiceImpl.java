package com.myblog11.myblog11.service.impl;

import com.myblog11.myblog11.entity.Comment;
import com.myblog11.myblog11.entity.Post;
import com.myblog11.myblog11.exception.ResourceNotFoundException;
import com.myblog11.myblog11.payload.CommentDto;
import com.myblog11.myblog11.repository.CommentRepository;
import com.myblog11.myblog11.repository.postRepository;
import com.myblog11.myblog11.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
    public class CommentServiceImpl implements CommentService{

    private postRepository postrepo;
    private CommentRepository commentRepository;
    private ModelMapper modelMapper;
    public CommentServiceImpl(CommentRepository commentRepository,postRepository postrepo,ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.postrepo = postrepo;
        this.modelMapper=modelMapper;
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

    @Override
    public CommentDto updateComment(long id, CommentDto commentDto, long postId) {

        Post post = postrepo.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("No post is found with this Id :" + postId)
        );

        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("no comment is found with id :" + id)
        );

//        modelMapper.map(commentDto,comment);
//       Comment updatedComment = commentRepository.save(comment);
//        CommentDto Dto = modelMapper.map(updatedComment, CommentDto.class);

        Comment comment1 = mapToEntity(commentDto);
        comment1.setPost(post);
        comment1.setId(comment.getId());

        Comment saved = commentRepository.save(comment1);
        CommentDto dto = mapToDto(saved);
        return dto;
    }
    CommentDto mapToDto(Comment comment){
        CommentDto commentDto = modelMapper.map(comment, CommentDto.class);
        return commentDto;
    }
    Comment mapToEntity(CommentDto commentDto){
        Comment comment = modelMapper.map(commentDto, Comment.class);
        return comment;
    }
}


