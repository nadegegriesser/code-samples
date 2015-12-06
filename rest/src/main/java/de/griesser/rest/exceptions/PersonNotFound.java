package de.griesser.rest.exceptions;

public class PersonNotFound extends Exception {

    private static final long serialVersionUID = 9176382068329878558L;

    public PersonNotFound(String id) {
        super("Person " + id + " not found");
    }

}
