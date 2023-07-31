package com.alperensertoglu.repository;

import com.alperensertoglu.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User,Long> {
    Optional<User> findOptionalByUsernameAndPassword(String username, String password);

    boolean existsByUsername(String username);

    Optional<User> findOptionalByUsername(String username);
}
