package de.griesser.rest.exceptions;

public class PersonNotFound extends Exception {

	private static final long serialVersionUID = -4928840232650294869L;

	public PersonNotFound(int id) {
		super("Person  " + id + " not found");
	}

}
