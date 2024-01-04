package com.myblog11.myblog11.service.impl;

import com.myblog11.myblog11.entity.Post;
import com.myblog11.myblog11.payload.Postdto;
import com.myblog11.myblog11.repository.postRepository;
import com.myblog11.myblog11.service.postservice;
import org.springframework.stereotype.Service;

@Service
public class postServiceImpl implements postservice {

    public postServiceImpl(postRepository postrepo) {
        this.postrepo = postrepo;
    }

    //Constructor Based Dependency injection gonna done here instead Of AUTOWIRED
    private postRepository postrepo;


    @Override
    public Postdto createPost(Postdto postdto) {

        Post post=new Post();
        post.setTitle(postdto.getTitle());
        post.setDescription(postdto.getDescription());
        post.setContent(postdto.getContent());
        Post savedBlog = postrepo.save(post);

        Postdto dto=new Postdto();

        dto.setContent(savedBlog.getContent());
        dto.setDescription(savedBlog.getDescription());
        dto.setTitle(savedBlog.getTitle());

        return dto;
    }
}
