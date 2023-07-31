package com.alperensertoglu.repository;

import com.alperensertoglu.repository.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICityRepository extends JpaRepository<City,Long> {
    boolean existsByName(String name);

    void deleteByIdOrName(Long id, String name);

    boolean existsByNameOrId(String name, Long id);
}
