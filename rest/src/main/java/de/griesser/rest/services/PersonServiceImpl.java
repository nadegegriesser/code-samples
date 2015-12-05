package de.griesser.rest.services;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import de.griesser.rest.resources.PersonResource;

public class PersonServiceImpl implements PersonService {

    private Map<String, PersonResource> persons;

    public PersonServiceImpl() {
        persons = new HashMap<String, PersonResource>();
    }

    public Collection<PersonResource> getAll() {
        return persons.values();
    }

    public PersonResource get(String id) {
        return persons.get(id);
    }

    public PersonResource create(PersonResource person) {
        String id = UUID.randomUUID().toString();
        person.setId(id);
        persons.put(id, person);
        return person;
    }

    public PersonResource update(String id, PersonResource person) {
        if (persons.containsKey(id)) {
            person.setId(id);
            persons.put(id, person);
            return person;
        }
        return null;
    }

    public void delete(String id) {
        persons.remove(id);
    }

}
