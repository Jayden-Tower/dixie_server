package com.example.dixie.servicesImpl;

import com.example.dixie.dto.UsersDto;
import com.example.dixie.models.Users;
import com.example.dixie.repositories.UsersRepository;
import com.example.dixie.services.UsersServices;
import com.example.dixie.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersServices {

    @Autowired
    private UsersRepository usersRepository;


    @Override
    public Response<Users> createUser(UsersDto usersDto) {
        try {
            Users user =  new Users();

            if (usersDto.getFirstName() == null){
                return new Response<>(true,400, "Firstname can not be empty");
            }

            if (usersDto.getLastName() ==  null){
                return new Response<>(true,400,"lastname can not be empty");
            }

            if(usersDto.getUsername() == null){
                return new Response<>(true,400,"Username can not be empty");
            }
            if(usersDto.getPhone() == null) {
                return new Response<>(true,400,"phone can not be empty");
            }

            if(!usersDto.getFirstName().isBlank() && !Objects.equals(usersDto.getFirstName(),user.getFirstName())){
                user.setFirstName(usersDto.getFirstName());
            }

            if(!usersDto.getLastName().isBlank() && !Objects.equals(usersDto.getLastName(),user.getLastName())){
                user.setLastName(usersDto.getLastName());
            }

            if(!usersDto.getUsername().isBlank() && !Objects.equals(usersDto.getUsername(),user.getUsername())){
                user.setUsername(usersDto.getUsername());
            }

            if(!usersDto.getPhone().isBlank() && !Objects.equals(usersDto.getPhone(), user.getPhone())){
                user.setPhone(usersDto.getPhone());
            }

            if(!usersDto.getGender().isBlank() && !Objects.equals(usersDto.getGender(),user.getGender())){
                user.setGender(usersDto.getGender());
            }

            user.setDeleted(false);

            Users users =  usersRepository.save(user);

            return new Response<>(false,201, users,"User registered successfully");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new Response<>(true,400,"operation failed");
    }

    @Override
    public Response<Users> getUserById(Long id) {
        try {
            Optional<Users> optionalUser =  usersRepository.findFirstById(id);

            if(optionalUser.isEmpty()){
                return new Response<>(true,404,"Nop record found with that id");
            }

            return new Response<>(false,200,optionalUser.get(), "Data found");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new Response<>(true,400,"operation failed");
    }

    @Override
    public Page<Users> getAllActiveUsers(Pageable pageable) {
        try {
            return usersRepository.findAllByDeletedFalse(pageable);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new PageImpl<>(new ArrayList<>());
    }
}
