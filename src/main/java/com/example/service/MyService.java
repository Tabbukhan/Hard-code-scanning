package com.example.service;

import com.example.document.Myuser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyService {

        List<Myuser> myusers = new ArrayList<>();

    @Autowired
    private MongoTemplate mongoTemplate;

    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

     public Myuser addUser(Myuser user){
      Myuser u = mongoTemplate.save(user);
         return u;
     }

    public List<Myuser> getUser() {
        this.myusers.add(new Myuser("1","aaa","123456"));
        this.myusers.add(new Myuser("2","abc","1234567"));
        this.myusers.add(new Myuser("3","aaajj","1234568"));
        this.myusers.add(new Myuser("4","aaakl","1234569"));
        return myusers;
    }
}
