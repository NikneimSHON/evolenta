package org.example.nikita.task.spring.repository;

import org.example.nikita.task.spring.entity.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<Person,Integer> {

}
