package org.example.nikita.task.spring.service;

import org.example.nikita.task.spring.entity.Message;
import org.example.nikita.task.spring.entity.Person;
import org.example.nikita.task.spring.repository.MessageRepository;
import org.example.nikita.task.spring.repository.PersonRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final MessageRepository messageRepository;

    public PersonService(PersonRepository personRepository, MessageRepository messageRepository) {
        this.personRepository = personRepository;
        this.messageRepository = messageRepository;
    }

    public Iterable<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public Optional<Person> getPersonById(Integer id) {
        return personRepository.findById(id);
    }

    public Person createPerson(Person person) {
        person.setId(null);
        return personRepository.save(person);
    }

    public Optional<Person> updatePerson(Integer id, Person person) {
        return personRepository.findById(id).map(existing -> {
            existing.setFirstname(person.getFirstname());
            existing.setSurname(person.getSurname());
            existing.setLastname(person.getLastname());
            existing.setBirthday(person.getBirthday());
            return personRepository.save(existing);
        });
    }

    public boolean deletePerson(Integer id) {
        if (!personRepository.existsById(id)) {
            return false;
        }
        personRepository.deleteById(id);
        return true;
    }


    public Optional<Message> getPersonMessageById(Integer personId, Integer messageId) {
        Optional<Person> person = personRepository.findById(personId);
        if (person.isEmpty()) {
            return Optional.empty();
        }

        return person.get().getMessages().stream()
                .filter(message -> message.getId().equals(messageId))
                .findFirst();
    }

    @Transactional
    public Optional<Message> addMessageToPerson(Integer personId, Message message) {
        Optional<Person> person = personRepository.findById(personId);

        if (person.isEmpty()) {
            return Optional.empty();
        }

        message.setId(null);
        Person existingPerson = person.get();
        existingPerson.addMessage(message);
        personRepository.save(existingPerson);

        Message savedMessage = messageRepository.save(message);
        return Optional.of(savedMessage);
    }

    @Transactional
    public boolean deleteMessageFromPerson(Integer personId, Integer messageId) {
        Optional<Person> person = personRepository.findById(personId);
        if (person.isEmpty()) {
            return false;
        }

        Person existingPerson = person.get();
        Optional<Message> messageToRemove = existingPerson.getMessages().stream()
                .filter(message -> message.getId().equals(messageId))
                .findFirst();

        if (messageToRemove.isEmpty()) {
            return false;
        }

        existingPerson.removeMessage(messageToRemove.get());
        personRepository.save(existingPerson);
        return true;
    }
}
