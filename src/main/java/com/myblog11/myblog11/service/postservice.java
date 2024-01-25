package com.myblog11.myblog11.service;

import com.myblog11.myblog11.payload.Postdto;

import java.util.List;

public interface postservice {
    static void getPostbyId(long id) {
    }

    Postdto createPost(Postdto postdto);


    Postdto getPostById(long Id);

    //Method Of Pagination concept Described/Created
    List<Postdto> getAllInPaginationFormat(int pageNo, int pageSize, String sortBy);

    List<Postdto> getAllData();

    List<Postdto>   getAllInPaginationFormat2(int pageNo, int pageSize, String sortBy,String sortDir);

    List<Postdto> getAllDataFromDB();
}
