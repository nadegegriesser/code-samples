package de.griesser.rest.services;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import de.griesser.rest.exceptions.PersonNotFound;
import de.griesser.rest.resources.PersonResource;

public class PersonServiceImpl implements PersonService {

    private Map<String, PersonResource> persons;

    public PersonServiceImpl() {
        persons = new HashMap<String, PersonResource>();
    }

    public Collection<PersonResource> getAll() {
        return persons.values();
    }

    public PersonResource get(String id) throws PersonNotFound {
        PersonResource res = persons.get(id);
        if (res == null) {
            throw new PersonNotFound(id);
        }
        return res;
    }

    public PersonResource create(PersonResource person) {
        String id = UUID.randomUUID().toString();
        person.setId(id);
        persons.put(id, person);
        return person;
    }

    public PersonResource update(String id, PersonResource person) throws PersonNotFound {
        if (!persons.containsKey(id)) {
            throw new PersonNotFound(id);
        }
        person.setId(id);
        persons.put(id, person);
        return person;
    }

    public void delete(String id) {
        persons.remove(id);
    }

}
