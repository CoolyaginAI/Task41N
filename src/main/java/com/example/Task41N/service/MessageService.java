package com.example.Task41N.service;

import com.example.Task41N.dto.Message;
import com.example.Task41N.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class MessageService {

    @Autowired
    MessageRepository repositoryMessage;

    public Iterable<Message> getMessage() {
        return repositoryMessage.findAll();
    }

    public Optional<Message> findMessageById(int id) {
        return repositoryMessage.findById(id);
    }

    public Message addMessage(Message message) {
        repositoryMessage.save(message);
        return message;
    }

    public boolean existsMessageById(int id) { return repositoryMessage.existsById(id); }

    public void updateMessage(int id, Message message) {
        repositoryMessage.findById(id).get().setTitle(message.getTitle());
        repositoryMessage.findById(id).get().setText(message.getText());
        repositoryMessage.findById(id).get().setTime(LocalDateTime.now());
        repositoryMessage.save(repositoryMessage.findById(id).get());
    }

    public void deleteMessageById(int id) {
        repositoryMessage.deleteById(id);
    }

}
