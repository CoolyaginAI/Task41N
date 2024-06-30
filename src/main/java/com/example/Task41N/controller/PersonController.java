package com.example.Task41N.controller;

import com.example.Task41N.service.PersonService;
import com.example.Task41N.dto.Message;
import com.example.Task41N.dto.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/person")
    public Iterable<Person> getPerson() {
        return personService.getPerson();
    }

    @GetMapping("/person/{id}")
    public Optional<Person> findPersonById(@PathVariable int id) {
        return personService.findPersonById(id);
    }

    @PostMapping("/person")
    public Person addPerson(@RequestBody Person person) {
        return personService.addPerson(person);
    }

    @PutMapping("/person/{id}")
    public HttpStatus updatePerson(@PathVariable int id, @RequestBody Person person) {

        HttpStatus status = personService.existById(id) ? HttpStatus.OK : HttpStatus.CREATED;

        if (status == HttpStatus.OK)
            personService.updatePerson(id, person);
        else
            personService.addPerson(person);

        return status;
    }

    @DeleteMapping("/person/{id}")
    public HttpStatus deletePerson(@PathVariable int id) {

        HttpStatus status = personService.existById(id) ? HttpStatus.OK : HttpStatus.BAD_REQUEST;

        if (status == HttpStatus.OK) {
            personService.deletePersonById(id);
        }

        return status;

    }

    @GetMapping("/person/{p_id}/message")
    public Iterable<Message> getMessageByPerson (@PathVariable int p_id) {
        return  personService.findPersonById(p_id).get().getMessages();
    }

    @GetMapping("/person/{p_id}/message/{m_id}")
    public Optional<Message> findMessageById(@PathVariable int p_id, @PathVariable int m_id) {
        return personService.findMessageById(p_id, m_id);
    }

    @PostMapping("/person/{p_id}/message")
    public HttpStatus addMessage(@PathVariable int p_id, @RequestBody Message message) {

        HttpStatus status = personService.existById(p_id) ? HttpStatus.OK : HttpStatus.BAD_REQUEST;

        if (status == HttpStatus.OK) {
            personService.addMessageToPerson(p_id, message);
        }

        return status;
    }

    @DeleteMapping("/person/{p_id}/message/{m_id}")
    public HttpStatus deleteMessage(@PathVariable int p_id, @PathVariable int m_id) {

        HttpStatus status = personService.existById(p_id) ? HttpStatus.OK : HttpStatus.BAD_REQUEST;

        if (status == HttpStatus.OK) {
            personService.deleteMessageToPerson(p_id, m_id);
        }

        return status;
    }

}
