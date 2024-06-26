package com.example.Task41N.service;

import com.example.Task41N.dto.Message;
import com.example.Task41N.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MessageService {

    @Autowired
    MessageRepository repositoryMessage;

    public void updateMessage(int id, Message message) {
        repositoryMessage.findById(id).get().setTitle(message.getTitle());
        repositoryMessage.findById(id).get().setText(message.getText());
        repositoryMessage.findById(id).get().setTime(LocalDateTime.now());
        repositoryMessage.save(repositoryMessage.findById(id).get());
    }
}
