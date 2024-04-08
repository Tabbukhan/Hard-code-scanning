package com.example.controller;

import com.example.document.Myuser;
import com.example.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class MyController {



    private MyService service;
    @Autowired
    public void setService(MyService service) {
        this.service = service;
    }

    @PostMapping("/add-user")
    public Myuser addUser(@RequestBody Myuser myUser){
       return service.addUser(myUser);
    }

    @GetMapping("/get-user")
    public List<Myuser> getUser(){
        return service.getUser();
    }
}
