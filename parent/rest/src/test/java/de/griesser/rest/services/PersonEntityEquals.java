package de.griesser.rest.services;

import org.easymock.IArgumentMatcher;

import de.griesser.persistence.entities.PersonEntity;

public class PersonEntityEquals implements IArgumentMatcher {

    private final PersonEntity expected;

    public PersonEntityEquals(PersonEntity expected) {
        this.expected = expected;
    }

    @Override
    public void appendTo(StringBuffer buffer) {
        buffer.append("eqPersonEntity(");
        buffer.append(expected);
        buffer.append(")");
    }

    @Override
    public boolean matches(Object actual) {
        if (!(actual instanceof PersonEntity)) {
            return false;
        }
        PersonEntity other = (PersonEntity) actual;
        String firstName = expected.getFirstName();
        if (firstName == null) {
            if (other.getFirstName() != null)
                return false;
        } else if (!firstName.equals(other.getFirstName()))
            return false;
        Long id = expected.getId();
        if (id == null) {
            if (other.getId() != null)
                return false;
        } else if (!id.equals(other.getId()))
            return false;
        String lastName = expected.getLastName();
        if (lastName == null) {
            if (other.getLastName() != null)
                return false;
        } else if (!lastName.equals(other.getLastName()))
            return false;
        return true;
    }

}
