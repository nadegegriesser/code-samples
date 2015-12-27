package de.griesser.persistence.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class PersonEntity implements Serializable {

    private static final long serialVersionUID = 4382376373655477269L;

    @Id
    @GeneratedValue
    private Long id;
    private String lastName;
    private String firstName;

    protected PersonEntity() {
    }

    public PersonEntity(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String toString() {
        return "PersonEntity [id=" + id + ", lastName=" + lastName + ", firstName=" + firstName + "]";
    }

}
