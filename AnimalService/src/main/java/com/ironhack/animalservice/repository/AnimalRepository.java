package com.ironhack.animalservice.repository;

import com.ironhack.animalservice.dao.AnimalDAO;
import com.ironhack.animalservice.enums.AnimalType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<AnimalDAO, Long> {

    List<AnimalDAO> getByAgeBetween(int start, int end);

    List<AnimalDAO> getByAgeBetweenAndType(int start, int end, AnimalType type);
}
