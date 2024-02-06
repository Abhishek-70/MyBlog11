package com.myblog11.myblog11.service.impl;

import com.myblog11.myblog11.entity.Post;
import com.myblog11.myblog11.exception.ResourceNotFoundException;
import com.myblog11.myblog11.payload.Postdto;
import com.myblog11.myblog11.repository.postRepository;
import com.myblog11.myblog11.service.postservice;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class postServiceImpl implements postservice {


    public postServiceImpl(postRepository postrepo,ModelMapper modelMapper) {
        this.postrepo = postrepo;
        this.modelMapper=modelMapper;
    }

    //Constructor Based Dependency injection gonna done here instead Of AUTOWIRED
    private postRepository postrepo;

private ModelMapper modelMapper;
    //Method Created for access all the element in the restControllerPractice
    @Override
    public List<Postdto> getAllDataFromDB() {
        List<Post> allData = postrepo.findAll();
        List<Postdto> alldto = allData.stream().map(m -> mapToDto(m)).collect(Collectors.toList());
        return alldto;
    }

    @Override
    public void deltePostById(long id) {
        postrepo.deleteById(id);
    }

    //Method Of Pagination concept Described/Created
    @Override
    public List<Postdto> getAllInPaginationFormat(int pageNo, int pageSize, String sortBy) {

        //1.//This method used before the sortby concept
//        PageRequest pagable = PageRequest.of(pageNo, pageSize);
//        Page<Post> posts2 = postrepo.findAll(pagable);
//        List<Postdto> listdtos = posts2.stream().map(n -> mapToDto(n)).collect(Collectors.toList());
//        return listdtos;

        //2. This Method is After introduce of sortBy Pagination Concept
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Post> allObj = postrepo.findAll(pageRequest);
        List<Post> posts= allObj.getContent();
        List<Postdto> postdto = posts.stream().map(n -> mapToDto(n)).collect(Collectors.toList());
        return postdto;
    }

    //3.here we extend the feature of sorting Direction(sortDir), which is default in ascending order.
    @Override
    public List<Postdto> getAllInPaginationFormat2(int pageNo, int pageSize, String sortBy, String sortDir) {
        //using Ternary Operator which is used to sort the code of if-else statement code,HERE understand the flow go with the pagable concept
        Sort sortobj = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        //then using overloaded (Of()) method
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize, sortobj);
        //repository layer used to perform operation with database
        Page<Post> allpost = postrepo.findAll(pageRequest);
        List<Postdto> postdtos = allpost.stream().map(n -> mapToDto(n)).collect(Collectors.toList());
        return postdtos;

    }
    @Override
    public List<Postdto> getAllData() {

        List<Post> postFoundAll = postrepo.findAll();
        //here we have to convert it into the PostDto class, Achieving this we Can simply Create A simple method ->
        //To convert Post -> PostDto ,And PostDto-> Into Post

        List<Postdto> AllDtos = postFoundAll.stream().map(n -> mapToDto(n)).collect(Collectors.toList());
        return AllDtos;
    }
    @Override
    public Postdto createPost(Postdto postdto) {

        Post post=new Post();
        post.setTitle(postdto.getTitle());
        post.setDescription(postdto.getDescription());
        post.setContent(postdto.getContent());
        Post savedBlog = postrepo.save(post);

        Postdto dto = mapToDto(savedBlog);
//
//        Postdto dto=new Postdto();
//        dto.setContent(savedBlog.getContent());
//        dto.setDescription(savedBlog.getDescription());
//        dto.setTitle(savedBlog.getTitle());

        return dto;
    }

    //Use to get the Data By Id with handling exception While no Occurance in the database

    @Override
    public Postdto getPostById(long id) {

        Post FoundedData = postrepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Post Not Found With Id"+ id)
        );

        Postdto setToDto=new Postdto();
        setToDto.setId(FoundedData.getId());
        setToDto.setTitle(FoundedData.getTitle());
        setToDto.setDescription(FoundedData.getDescription());
        setToDto.setContent(FoundedData.getContent());
        return setToDto;
    }

    //Create Method to Convert Post(Entity) into PostDto(Dto class)
    public Postdto mapToDto(Post post){
//        Postdto dto=new Postdto();
//        dto.setId(post.getId());
//        dto.setTitle(post.getTitle());
//        dto.setContent(post.getContent());
//        dto.setDescription(post.getDescription());
        //Using the modelMapper External Libre=ary we short the code length such that it will improve the copying the data
        //from the entity to dto or dto to entity
        Postdto dtos= modelMapper.map(post, Postdto.class);
         return  dtos;
        }

        //Create A Method to Convert The Postdto(Dto Class) into Post(Entity Class)
   public Post mapToPost(Postdto dto){
        Post post=modelMapper.map(dto,Post.class);
        return post;
    }
}
