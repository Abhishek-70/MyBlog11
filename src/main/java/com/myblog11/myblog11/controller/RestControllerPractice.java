package com.myblog11.myblog11.controller;

import com.myblog11.myblog11.payload.Postdto;
import com.myblog11.myblog11.service.impl.postServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/practice")
public class RestControllerPractice {

    private postServiceImpl postserv;

    public RestControllerPractice(postServiceImpl postserv) {
        this.postserv = postserv;
    }


    //URL to access this method(http://localhost:8080/api/practice)
    @GetMapping
    public List<Postdto> getAllDetails(){
        List<Postdto> allDataFromDB = postserv.getAllDataFromDB();
        return allDataFromDB;
    }

    //Now we crate the logic of pagination and sorting we create the method to access the concept of the pagination and sorting

   //Url to access this method(http://localhost:8080/api/practice/pagination2?pageNo=1&pageSize=4)
    @GetMapping("/pagination2")
    public List<Postdto> getAllData(
            @RequestParam (name = "pageNo",required = false,defaultValue="0")int pageNo,
            @RequestParam(name="pageSize",required = false,defaultValue = "4")int pageSize
    ){

        List<Postdto> allDataFromDB2 = postserv.getAllInPaginationFormat(pageNo,pageSize);
        return allDataFromDB2;
    }

}
