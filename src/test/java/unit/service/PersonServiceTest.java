package unit.service;

import org.example.nikita.task.spring.entity.Message;
import org.example.nikita.task.spring.entity.Person;
import org.example.nikita.task.spring.repository.MessageRepository;
import org.example.nikita.task.spring.repository.PersonRepository;
import org.example.nikita.task.spring.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @Mock
    private MessageRepository messageRepository;

    @InjectMocks
    private PersonService personService;

    private Person person;
    private Message message;

    @BeforeEach
    void setUp() {
        person = new Person();
        person.setId(1);
        person.setFirstname("Ivan");
        person.setSurname("Ivanov");

        message = new Message();
        message.setId(1);
        message.setTitle("Hello");
        message.setText("World");
        person.addMessage(message);
    }

    @Test
    void getPersonById_WhenExists_ShouldReturnPerson() {
        when(personRepository.findById(1)).thenReturn(Optional.of(person));

        Optional<Person> result = personService.getPersonById(1);

        assertThat(result).isPresent();
        assertThat(result.get().getFirstname()).isEqualTo("Ivan");
    }

    @Test
    void updatePerson_WhenExists_ShouldUpdateFieldsAndKeepMessages() {
        Person incoming = new Person();
        incoming.setFirstname("Petr");
        incoming.setSurname("Petrov");

        when(personRepository.findById(1)).thenReturn(Optional.of(person));
        when(personRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        Optional<Person> result = personService.updatePerson(1, incoming);

        assertThat(result).isPresent();
        assertThat(result.get().getFirstname()).isEqualTo("Petr");
        assertThat(result.get().getMessages()).hasSize(1);
    }

    @Test
    void deletePerson_WhenExists_ShouldReturnTrue() {
        when(personRepository.existsById(1)).thenReturn(true);

        boolean result = personService.deletePerson(1);

        assertThat(result).isTrue();
        verify(personRepository).deleteById(1);
    }

    @Test
    void getPersonMessages_WhenExists_ShouldReturnMessages() {
        when(personRepository.findById(1)).thenReturn(Optional.of(person));

        Optional<List<Message>> result = personService.getPersonMessages(1);

        assertThat(result).isPresent();
        assertThat(result.get()).hasSize(1);
        assertThat(result.get().get(0).getTitle()).isEqualTo("Hello");
    }

    @Test
    void addMessageToPerson_WhenPersonExists_ShouldReturnSavedMessage() {
        Message newMessage = new Message();
        newMessage.setTitle("New");

        when(personRepository.findById(1)).thenReturn(Optional.of(person));
        when(personRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));
        when(messageRepository.save(any())).thenAnswer(inv -> {
            Message m = inv.getArgument(0);
            m.setId(2);
            return m;
        });

        Optional<Message> result = personService.addMessageToPerson(1, newMessage);

        assertThat(result).isPresent();
        assertThat(result.get().getId()).isEqualTo(2);
    }

    @Test
    void deleteMessageFromPerson_WhenBothExist_ShouldReturnTrueAndRemove() {
        when(personRepository.findById(1)).thenReturn(Optional.of(person));
        when(personRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        boolean result = personService.deleteMessageFromPerson(1, 1);

        assertThat(result).isTrue();
        assertThat(person.getMessages()).isEmpty();
    }
}