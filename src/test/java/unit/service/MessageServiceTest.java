package unit.service;

import org.example.nikita.task.spring.entity.Message;
import org.example.nikita.task.spring.repository.MessageRepository;
import org.example.nikita.task.spring.service.MessageService;
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
class MessageServiceTest {

    @Mock
    private MessageRepository messageRepository;

    @InjectMocks
    private MessageService messageService;

    private Message message;

    @BeforeEach
    void setUp() {
        message = new Message();
        message.setId(1);
        message.setTitle("Test title");
        message.setText("Test text");
    }

    @Test
    void getAllMessages_ShouldReturnAllMessages() {
        when(messageRepository.findAll()).thenReturn(List.of(message));

        Iterable<Message> result = messageService.getAllMessages();

        assertThat(result).containsExactly(message);
    }

    @Test
    void getMessageById_WhenExists_ShouldReturnMessage() {
        when(messageRepository.findById(1)).thenReturn(Optional.of(message));

        Optional<Message> result = messageService.getMessageById(1);

        assertThat(result).isPresent();
        assertThat(result.get().getTitle()).isEqualTo("Test title");
    }

    @Test
    void createMessage_ShouldResetIdAndSave() {
        when(messageRepository.save(any())).thenAnswer(inv -> {
            Message m = inv.getArgument(0);
            m.setId(1);
            return m;
        });

        Message result = messageService.createMessage(message);

        assertThat(result.getId()).isEqualTo(1);
        verify(messageRepository).save(any(Message.class));
    }

    @Test
    void updateMessage_WhenExists_ShouldUpdateFields() {
        Message incoming = new Message();
        incoming.setTitle("New title");
        incoming.setText("New text");

        when(messageRepository.findById(1)).thenReturn(Optional.of(message));
        when(messageRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        Optional<Message> result = messageService.updateMessage(1, incoming);

        assertThat(result).isPresent();
        assertThat(result.get().getTitle()).isEqualTo("New title");
        assertThat(result.get().getId()).isEqualTo(1);
    }

    @Test
    void deleteMessage_WhenExists_ShouldReturnTrue() {
        when(messageRepository.existsById(1)).thenReturn(true);

        boolean result = messageService.deleteMessage(1);

        assertThat(result).isTrue();
        verify(messageRepository).deleteById(1);
    }

    @Test
    void deleteMessage_WhenNotExists_ShouldReturnFalse() {
        when(messageRepository.existsById(99)).thenReturn(false);

        boolean result = messageService.deleteMessage(99);

        assertThat(result).isFalse();
        verify(messageRepository, never()).deleteById(any());
    }
}