package de.griesser.rest.services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import de.griesser.persistence.entities.PersonEntity;
import de.griesser.persistence.repositories.PersonRepository;
import de.griesser.rest.exceptions.PersonNotFound;
import de.griesser.rest.resources.PersonResource;

public class PersonServiceImpl implements PersonService {

    @Autowired(required = true)
    private PersonRepository repository;

    public void setRepository(PersonRepository repository) {
        this.repository = repository;
    }

    public Collection<PersonResource> getAll() {
        Collection<PersonResource> res = new ArrayList<>();
        for (PersonEntity entity : repository.findAll()) {
            res.add(new PersonResource(entity));
        }
        return res;
    }

    public PersonResource get(String id) throws PersonNotFound {
        PersonEntity entity = repository.findOne(Long.parseLong(id));
        if (entity == null) {
            throw new PersonNotFound(id);
        }
        return new PersonResource(entity);
    }

    public PersonResource create(PersonResource person) {
        PersonEntity entity = repository.save(new PersonEntity(person.getFirstName(), person.getLastName()));
        return new PersonResource(entity);
    }

    public PersonResource update(String id, PersonResource person) throws PersonNotFound {
        PersonEntity entity = repository.findOne(Long.parseLong(id));
        if (entity == null) {
            throw new PersonNotFound(id);
        }
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity = repository.save(entity);
        return new PersonResource(entity);
    }

    public void delete(String id) {
        repository.delete(Long.parseLong(id));
    }

}
