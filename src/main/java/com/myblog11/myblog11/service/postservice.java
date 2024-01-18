package com.myblog11.myblog11.service;

import com.myblog11.myblog11.payload.Postdto;

import java.util.List;

public interface postservice {
    static void getPostbyId(long id) {
    }

    Postdto createPost(Postdto postdto);


    Postdto getPostById(long Id);

    List<Postdto> getAllData();

    List<Postdto>   getAllInPaginationFormat(int pageNo,int pageSize);

    List<Postdto> getAllDataFromDB();
}
