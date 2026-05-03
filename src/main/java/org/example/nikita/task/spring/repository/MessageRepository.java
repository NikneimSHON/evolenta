package org.example.nikita.task.spring.repository;

import org.example.nikita.task.spring.entity.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends CrudRepository<Message, Integer> {

}
