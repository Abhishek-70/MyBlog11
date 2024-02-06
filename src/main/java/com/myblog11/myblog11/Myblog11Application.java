package com.myblog11.myblog11;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Myblog11Application {

	public static void main(String[] args) {

		SpringApplication.run(Myblog11Application.class, args);
		//Stream api We Learn On Today Topic
	}

	//Generally it's used to short the code length, by its pre-define functionality,which is copy the content of one file to another file
	//like copying the content of CommentDto -> Comment,post->postDto
	@Bean
	public ModelMapper getModelMapper(){
		return  new ModelMapper();
	}

}
