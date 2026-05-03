package org.example.nikita.task.spring.controller;

import org.example.nikita.task.spring.entity.Message;
import org.example.nikita.task.spring.entity.Person;
import org.example.nikita.task.spring.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Person>> getAllPersons() {
        return ResponseEntity.ok(personService.getAllPersons());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Integer id) {
        return personService.getPersonById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        Person created = personService.createPerson(person);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Integer id, @RequestBody Person person) {
        return personService.updatePerson(id, person)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Integer id) {
        if (personService.deletePerson(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{p_id}/message")
    public ResponseEntity<List<Message>> getPersonMessages(@PathVariable("p_id") Integer personId) {
        return personService.getPersonMessages(personId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{p_id}/message/{m_id}")
    public ResponseEntity<Message> getPersonMessageById(
            @PathVariable("p_id") Integer personId,
            @PathVariable("m_id") Integer messageId) {
        return personService.getPersonMessageById(personId, messageId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{p_id}/message")
    public ResponseEntity<Message> addMessageToPerson(
            @PathVariable("p_id") Integer personId,
            @RequestBody Message message) {
        return personService.addMessageToPerson(personId, message)
                .map(msg -> ResponseEntity.status(HttpStatus.CREATED).body(msg))
                .orElse(ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{p_id}/message/{m_id}")
    public ResponseEntity<Void> deleteMessageFromPerson(
            @PathVariable("p_id") Integer personId,
            @PathVariable("m_id") Integer messageId) {
        if (personService.deleteMessageFromPerson(personId, messageId)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
