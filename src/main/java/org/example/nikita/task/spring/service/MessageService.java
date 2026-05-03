package org.example.nikita.task.spring.service;

import org.example.nikita.task.spring.entity.Message;
import org.example.nikita.task.spring.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Iterable<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public Optional<Message> getMessageById(Integer id) {
        return messageRepository.findById(id);
    }

    public Message createMessage(Message message) {
        message.setId(null);
        return messageRepository.save(message);
    }

    public Optional<Message> updateMessage(Integer id, Message message) {
        return messageRepository.findById(id).map(existing -> {
            existing.setTitle(message.getTitle());
            existing.setText(message.getText());
            return messageRepository.save(existing);
        });
    }

    public boolean deleteMessage(Integer id) {
        if (!messageRepository.existsById(id)) {
            return false;
        }
        messageRepository.deleteById(id);
        return true;
    }
}
