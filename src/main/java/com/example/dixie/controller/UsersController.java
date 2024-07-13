package com.example.dixie.controller;

import com.example.dixie.dto.UsersDto;
import com.example.dixie.models.Users;
import com.example.dixie.services.UsersServices;
import com.example.dixie.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@RestController
@Component
@RequestMapping("api/v1/users")
public class UsersController {

    @Autowired
    private UsersServices usersServices;

    @PostMapping("create")
    public ResponseEntity<?> createUser(@RequestBody UsersDto usersDto){
        Response<Users> response =  usersServices.createUser(usersDto);

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getUsers(@RequestParam(value = "page", defaultValue = "0")Integer page,
                                      @RequestParam(value = "size", defaultValue = "25")Integer size){
        PageRequest pageRequest =  PageRequest.of(page,size);
        Page<Users> usersPage =  usersServices.getAllActiveUsers(pageRequest);

        return ResponseEntity.ok().body(usersPage);
    }

    @GetMapping("/get-by-id/{id}")
    public  ResponseEntity<?> getUserById( @PathVariable(value = "id") Long id){
        Response<Users> response =  usersServices.getUserById(id);

        return ResponseEntity.ok().body(response);
    }

}
