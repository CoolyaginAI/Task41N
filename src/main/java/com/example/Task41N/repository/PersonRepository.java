package com.example.Task41N.repository;

import com.example.Task41N.dto.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository  extends CrudRepository<Person, Integer> {
}
