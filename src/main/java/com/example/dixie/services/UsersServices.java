package com.example.dixie.services;

import com.example.dixie.dto.UsersDto;
import com.example.dixie.models.Users;
import com.example.dixie.utils.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface UsersServices {

    Response<Users> createUser(UsersDto usersDto);

    Response<Users> getUserById(Long id);

    Page<Users> getAllActiveUsers(Pageable pageable);

}
