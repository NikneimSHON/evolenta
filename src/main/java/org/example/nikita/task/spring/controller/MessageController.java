package org.example.nikita.task.spring.controller;

import org.example.nikita.task.spring.entity.Message;
import org.example.nikita.task.spring.entity.Person;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
public class MessageController {

    private List<Message> messages = new ArrayList<>(Arrays.asList(
            new Message(1, "test1", "test1", LocalDateTime.of(2000, 1, 1, 10, 10, 10)),
            new Message(2, "test2", "test2", LocalDateTime.of(2001, 2, 2, 11, 11, 11)),
            new Message(3, "test3", "test3", LocalDateTime.of(2002, 3, 3, 12, 12, 12)),
            new Message(4, "test4", "test4", LocalDateTime.of(2003, 4, 4, 13, 13, 13))
    ));

    @GetMapping("/message")
    public Iterable<Message> getMessages() {
        return messages;
    }

    @GetMapping("/message/{id}")
    public Optional<Message> getMessage(@PathVariable Integer id) {
       return  messages.stream().filter(m -> m.getId() == id).findFirst();
    }

    @DeleteMapping("/message/{id}")
    public void deleteMessage(@PathVariable Integer id) {
        messages.removeIf(m -> m.getId() == id);
    }

    @PostMapping("/message")
    public Message addMessage(@RequestBody Message message) {
        messages.add(message);
        return message;
    }

    @PutMapping("/message/{id}")
    public ResponseEntity<Message> updatePerson(@PathVariable int id, @RequestBody Message message) {
        int index = - 1;
        for (Message m : messages) {
            if (m.getId() == id) {
                index = messages.indexOf(m);
                messages.set(index, message);
            }
        }
        return index == -1
                ? new ResponseEntity<>(addMessage(message), HttpStatus.CREATED)
                : new ResponseEntity<>(message, HttpStatus.OK);
    }
}
