package com.example.workshopmongo.config;

import com.example.workshopmongo.domain.Post;
import com.example.workshopmongo.domain.User;
import com.example.workshopmongo.domain.dto.AuthorDTO;
import com.example.workshopmongo.repository.PostRepository;
import com.example.workshopmongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        userRepository.deleteAll();
        postRepository.deleteAll();

        User a1 = new User(null, "Maria Brown", "maria@gmail.com");
        User a2 = new User(null, "Alex Green", "alex@gmail.com");
        User a3 = new User(null, "Bob Grey", "bob@gmail.com");

        userRepository.saveAll(Arrays.asList(a1, a2, a3));

        Post p1 = new Post(null,sdf.parse("21/03/2018"),"Viagem apenas","Vou viajar para Portugal",new AuthorDTO(a1));
        Post p2 = new Post(null,sdf.parse("23/03/2018"),"Bom dia","Portugal é lindo",new AuthorDTO(a1));

        postRepository.saveAll(Arrays.asList(p1,p2));

    }
}