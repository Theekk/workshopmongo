package com.example.workshopmongo.config;

import com.example.workshopmongo.domain.Post;
import com.example.workshopmongo.domain.User;
import com.example.workshopmongo.domain.dto.AuthorDTO;
import com.example.workshopmongo.domain.dto.CommentDTO;
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

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");

        userRepository.saveAll(Arrays.asList(maria, alex, bob));

        Post p1 = new Post(null,sdf.parse("21/03/2018"),"Viagem apenas","Vou viajar para Portugal",new AuthorDTO(maria));
        Post p2 = new Post(null,sdf.parse("23/03/2018"),"Bom dia","Portugal é lindo",new AuthorDTO(maria));

        CommentDTO c1 = new CommentDTO("Boa",sdf.parse("21/03/2018"), new AuthorDTO(alex));
        CommentDTO c2 = new CommentDTO("Aproveite",sdf.parse("21/03/2018"), new AuthorDTO(bob));
        CommentDTO c3 = new CommentDTO("Realmente é lindo",sdf.parse("23/03/2018"), new AuthorDTO(alex));

        p1.getComments().addAll(Arrays.asList(c1,c2));
        p2.getComments().addAll(Arrays.asList(c3));


        postRepository.saveAll(Arrays.asList(p1,p2));

        maria.getPosts().addAll(Arrays.asList(p1,p2));
        userRepository.save(maria);
    }
}