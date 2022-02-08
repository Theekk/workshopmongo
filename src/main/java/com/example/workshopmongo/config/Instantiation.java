package com.example.workshopmongo.config;

import com.example.workshopmongo.domain.User;
import com.example.workshopmongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        userRepository.deleteAll();

        User a1 = new User(null, "Maria Brown", "maria@gmail.com");
        User a2 = new User(null, "Alex Green", "alex@gmail.com");
        User a3 = new User(null, "Bob Grey", "bob@gmail.com");

        userRepository.saveAll(Arrays.asList(a1, a2, a3));
    }
}