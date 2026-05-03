package org.example.nikita.task.spring.repository;

import org.example.nikita.task.spring.entity.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message, Integer> {


}
