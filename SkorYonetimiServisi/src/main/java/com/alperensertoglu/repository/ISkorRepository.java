package com.alperensertoglu.repository;

import com.alperensertoglu.repository.entity.Skor;
import com.alperensertoglu.service.SkorService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ISkorRepository extends JpaRepository<Skor,Long> {
    Optional<Skor> findOptionalByUserid(Long userId);
}
