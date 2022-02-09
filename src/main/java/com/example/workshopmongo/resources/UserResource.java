package com.example.workshopmongo.resources;

import com.example.workshopmongo.domain.Post;
import com.example.workshopmongo.domain.User;
import com.example.workshopmongo.domain.dto.UserDTO;
import com.example.workshopmongo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<UserDTO>> findAll(){
        List<User> list = userService.findAll();
        List<UserDTO> listDTO = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable String id){
        User obj = userService.findById(id);
        return ResponseEntity.ok().body(new UserDTO(obj));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@RequestBody UserDTO userDTO){
        User obj = userService.fromDTO(userDTO);
        obj = userService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @RequestMapping(value="/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable String id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value="/{id}",method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@RequestBody UserDTO userDTO, @PathVariable String id){
        User obj = userService.fromDTO(userDTO);
        obj.setId(id);
        obj = userService.update(obj);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}/posts")
    public ResponseEntity<List<Post>> findPosts(@PathVariable String id){
        User obj = userService.findById(id);
        return ResponseEntity.ok().body(obj.getPosts());
    }
}