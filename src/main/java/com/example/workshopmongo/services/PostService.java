package com.example.workshopmongo.services;

import com.example.workshopmongo.domain.Post;
import com.example.workshopmongo.domain.User;
import com.example.workshopmongo.domain.dto.UserDTO;
import com.example.workshopmongo.repository.PostRepository;
import com.example.workshopmongo.repository.UserRepository;
import com.example.workshopmongo.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Post findById(String id){
        Optional<Post> obj = postRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Object Not Found!"));
    }
}