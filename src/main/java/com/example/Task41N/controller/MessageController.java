package com.example.Task41N.controller;

import com.example.Task41N.service.MessageService;
import com.example.Task41N.dto.Message;
import com.example.Task41N.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;


@RestController
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private MessageService messageService;

    @GetMapping("/message")
    public Iterable<Message> getMessage() {
        return messageService.getMessage();
    }

    @GetMapping("/message/{id}")
    public Optional<Message> findMessageById(@PathVariable int id) {
        return messageService.findMessageById(id);
    }

    @PostMapping("/message")
    public Message addMessage(@RequestBody Message message) {
        return messageService.addMessage(message);
    }

    @PutMapping("/message/{id}")
    public HttpStatus updateMessage(@PathVariable int id, @RequestBody Message message) {

        HttpStatus status = messageService.existsMessageById(id) ? HttpStatus.OK : HttpStatus.BAD_REQUEST;

        if (status == HttpStatus.OK) {
            messageService.updateMessage(id, message);
        }

        return status;

    }

    @DeleteMapping("/message/{id}")
    public void deleteMessage(@PathVariable int id) {
        messageService.deleteMessageById(id);
    }

}
