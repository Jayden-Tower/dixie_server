package com.example.dixie.repositories;

import com.example.dixie.models.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users,Long> {

    Optional<Users> findFirstById(Long id);

    Page<Users> findAllByDeletedFalse(Pageable pageable);
}
