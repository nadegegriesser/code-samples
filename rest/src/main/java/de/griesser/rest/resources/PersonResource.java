package de.griesser.rest.resources;

import java.util.Date;

public class PersonResource {

	private Integer id;
	private String lastName;
	private String firstName;
	private Date dateOfBirth;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@Override
	public String toString() {
		return "PersonResource [id=" + id + ", lastName=" + lastName + ", firstName=" + firstName + ", dateOfBirth="
				+ dateOfBirth + "]";
	}

}
