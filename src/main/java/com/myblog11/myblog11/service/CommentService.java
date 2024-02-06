package com.myblog11.myblog11.service;

import com.myblog11.myblog11.payload.CommentDto;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto, long postId);

    void deleteComments(long id);

    CommentDto updateComment(long id, CommentDto commentDto, long postId);
}
