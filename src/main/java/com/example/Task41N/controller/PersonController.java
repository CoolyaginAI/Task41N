package com.example.Task41N.controller;

import com.example.Task41N.service.PersonService;
import com.example.Task41N.dto.Message;
import com.example.Task41N.dto.Person;
import com.example.Task41N.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonService personService;

    @GetMapping("/person")
    public Iterable<Person> getPerson() {
        return personRepository.findAll();
    }

    @GetMapping("/person/{id}")
    public Optional<Person> findPersonById(@PathVariable int id) {
        return personRepository.findById(id);
    }

    @PostMapping("/person")
    public Person addPerson(@RequestBody Person person) {
        personRepository.save(person);
        return person;
    }

    @PutMapping("/person/{id}")
    public HttpStatus updatePerson(@PathVariable int id, @RequestBody Person person) {

        HttpStatus status = personRepository.existsById(id) ? HttpStatus.OK : HttpStatus.CREATED;

        if (status == HttpStatus.OK) {
            personService.updatePerson(id, person);
        }
        else {
            personRepository.save(person);
        }

        return status;
    }

    @DeleteMapping("/person/{id}")
    public HttpStatus deletePerson(@PathVariable int id) {

        HttpStatus status = personRepository.existsById(id) ? HttpStatus.OK : HttpStatus.BAD_REQUEST;

        if (status == HttpStatus.OK) {
            personRepository.deleteById(id);
        }

        return status;

    }

    @GetMapping("/person/{p_id}/message")
    public Iterable<Message> getMessageByPerson (@PathVariable int p_id) {
        return  personRepository.findById(p_id).get().getMessages();
    }

    @GetMapping("/person/{p_id}/message/{m_id}")
    public Optional<Message> findMessageById(@PathVariable int p_id, @PathVariable int m_id) {
        return personService.findMessageById(p_id, m_id);
    }

    @PostMapping("/person/{p_id}/message")
    public HttpStatus addMessage(@PathVariable int p_id, @RequestBody Message message) {

        HttpStatus status = personRepository.existsById(p_id) ? HttpStatus.OK : HttpStatus.BAD_REQUEST;

        if (status == HttpStatus.OK) {
            personService.addMessageToPerson(p_id, message);
        }

        return status;
    }

    @DeleteMapping("/person/{p_id}/message/{m_id}")
    public HttpStatus deleteMessage(@PathVariable int p_id, @PathVariable int m_id, @RequestBody Message message) {

        HttpStatus status = personRepository.existsById(p_id) ? HttpStatus.OK : HttpStatus.BAD_REQUEST;

        if (status == HttpStatus.OK) {
            personService.deleteMessageToPerson(p_id, m_id);
        }

        return status;
    }

}
