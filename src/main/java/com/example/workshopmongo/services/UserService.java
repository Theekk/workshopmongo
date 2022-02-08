package com.example.workshopmongo.services;

import com.example.workshopmongo.domain.User;
import com.example.workshopmongo.repository.UserRepository;
import com.example.workshopmongo.resources.UserResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }
}