package com.example.Task41N.service;

import com.example.Task41N.dto.Message;
import com.example.Task41N.dto.Person;
import com.example.Task41N.repository.MessageRepository;
import com.example.Task41N.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    PersonRepository repositoryPerson;

    @Autowired
    MessageRepository repositoryMessage;

    public Iterable<Person> getPerson() {
        return repositoryPerson.findAll();
    }

    public Optional<Person> findPersonById(int idPerson) {
        return repositoryPerson.findById(idPerson);
    }

    public Person addPerson(Person person) {
        repositoryPerson.save(person);
        return person;
    }

    public boolean existById(int idPerson) {
        return repositoryPerson.existsById(idPerson);
    }

    public void updatePerson (int id, Person person) {
        repositoryPerson.findById(id).get().setFirstname(person.getFirstname());
        repositoryPerson.findById(id).get().setSurname(person.getSurname());
        repositoryPerson.findById(id).get().setLastname(person.getLastname());
        repositoryPerson.findById(id).get().setBirthday(person.getBirthday());
        repositoryPerson.save(repositoryPerson.findById(id).get());
    }

    public void deletePersonById (int idPerson) {
        repositoryPerson.deleteById(idPerson);
    }

    public Person addMessageToPerson (int idMessage, Message message) {

        Person person = repositoryPerson.findById(idMessage).get();
        message.setPerson(person);
        message.setTime(LocalDateTime.now());
        person.getMessages().add(message);

        return repositoryPerson.save(person);
    }

    public void deleteMessageToPerson (int idPerson, int idMessage) {
        List<Message> messages = repositoryPerson.findById(idPerson).get().getMessages();

        for (int i=0; i<messages.size(); i++)
            if (messages.get(i).getId() == idMessage) { messages.remove(i);}

        repositoryMessage.deleteById(idMessage);
    }

    public Optional<Message> findMessageById (int idPerson, int idMessage) {
        List<Message> messages = repositoryPerson.findById(idPerson).get().getMessages();
        Optional<Message> findMessage = Optional.empty();

        for (int i=0; i<messages.size(); i++)
            if (messages.get(i).getId() == idMessage) {
                findMessage = Optional.ofNullable(messages.get(i));
            }

        return findMessage;
    }


}
