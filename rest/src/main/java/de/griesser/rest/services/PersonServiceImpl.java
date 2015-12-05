package de.griesser.rest.services;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import de.griesser.rest.exceptions.PersonNotFound;
import de.griesser.rest.resources.PersonResource;

public class PersonServiceImpl implements PersonService {
	
	private Map<Integer, PersonResource> persons;
	
	public PersonServiceImpl() {
		persons = new HashMap<Integer, PersonResource>();
	}

	public Collection<PersonResource> getAll() {
		return persons.values();
	}

	public PersonResource get(int id) throws PersonNotFound {
		if (!persons.containsKey(id)) {
			throw new PersonNotFound(id);
		}
		return persons.get(id);
	}

	public PersonResource create(PersonResource person) {
		Integer id = null;
		person.setId(id);
		return person;
	}

	public PersonResource update(int id, PersonResource person) throws PersonNotFound {
		if (!persons.containsKey(id)) {
			throw new PersonNotFound(id);
		}
		person.setId(id);
		persons.put(id, person);
		return person;
	}

	public void delete(int id) throws PersonNotFound {
		if (!persons.containsKey(id)) {
			throw new PersonNotFound(id);
		}
		persons.remove(id);
	}

}
