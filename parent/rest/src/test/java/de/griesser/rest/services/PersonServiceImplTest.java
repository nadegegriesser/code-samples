package de.griesser.rest.services;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.reportMatcher;
import static org.easymock.EasyMock.verify;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.easymock.EasyMockRunner;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.griesser.persistence.entities.PersonEntity;
import de.griesser.persistence.repositories.PersonRepository;
import de.griesser.rest.exceptions.PersonNotFound;
import de.griesser.rest.resources.PersonResource;

@RunWith(EasyMockRunner.class)
public class PersonServiceImplTest {

    private static final String LAST_NAME = "Doe";
    private static final String FIRST_NAME = "John";
    private static final String OTHER_LAST_NAME = "Musterman";
    private static final String OTHER_FIRST_NAME = "Max";
    private static final long ID = 1234L;
    private static final String NFE_ID = "abc";
    private static final long NOT_FOUND_ID = 5678L;

    @TestSubject
    private PersonServiceImpl sut = new PersonServiceImpl();

    @Mock
    private PersonRepository repository;

    @Test
    public void testGetAllEmptyResult() {
        // given
        expect(repository.findAll()).andReturn(Collections.emptyList());
        replay(repository);

        // when
        Collection<PersonResource> res = sut.getAll();

        // then
        Assert.assertNotNull(res);
        Assert.assertTrue(res.isEmpty());
        verify(repository);
    }

    @Test
    public void testGetAll() {
        // given
        expect(repository.findAll()).andReturn(Arrays.asList(createPersistedPersonEntity()));
        replay(repository);

        // when
        Collection<PersonResource> res = sut.getAll();

        // then
        Assert.assertNotNull(res);
        Assert.assertEquals(1, res.size());
        checkPerson(res.iterator().next());
        verify(repository);
    }

    @Test(expected = NumberFormatException.class)
    public void testGetNumberFormatException() throws PersonNotFound {
        // given

        // when
        sut.get(NFE_ID);

        // then
    }

    @Test(expected = PersonNotFound.class)
    public void testGetPersonNotFound() throws PersonNotFound {
        // given
        expect(repository.findOne(NOT_FOUND_ID)).andReturn(null);
        replay(repository);

        // when
        try {
            sut.get("" + NOT_FOUND_ID);
        } finally {
            // then
            verify(repository);
        }
    }

    @Test
    public void testGet() throws PersonNotFound {
        // given
        expect(repository.findOne(ID)).andReturn(createPersistedPersonEntity());
        replay(repository);

        // when
        PersonResource res = sut.get("" + ID);

        // then
        checkPerson(res);
        verify(repository);
    }

    @Test
    public void testCreate() {
        // given
        expect(repository.save(eqPersonEntity(createPersonEntity()))).andReturn(createPersistedPersonEntity());
        replay(repository);

        // when
        PersonResource res = sut.create(createPersonResource());

        // then
        checkPerson(res);
        verify(repository);
    }

    @Test(expected = NumberFormatException.class)
    public void testUpdateNumberFormatException() throws PersonNotFound {
        // given

        // when
        sut.update(NFE_ID, createPersonResource());

        // then
    }

    @Test(expected = PersonNotFound.class)
    public void testUpdatePersonNotFound() throws PersonNotFound {
        // given
        expect(repository.findOne(NOT_FOUND_ID)).andReturn(null);
        replay(repository);

        // when
        try {
            sut.update("" + NOT_FOUND_ID, createPersonResource());
        } finally {
            // then
            verify(repository);
        }
    }

    @Test
    public void testUpdate() throws PersonNotFound {
        // given
        expect(repository.findOne(ID)).andReturn(createPersistedPersonEntity());
        expect(repository.save(eqPersonEntity(createOtherPersistedPersonEntity())))
                .andReturn(createOtherPersistedPersonEntity());
        replay(repository);

        // when
        PersonResource res = sut.update("" + ID, createOtherPersonResource());

        // then
        checkOtherPerson(res);
        verify(repository);
    }

    @Test(expected = NumberFormatException.class)
    public void testDeleteNumberFormatException() {
        // given

        // when
        sut.delete(NFE_ID);

        // then
    }

    @Test
    public void testDelete() {
        // given
        repository.delete(ID);
        replay(repository);

        // when
        sut.delete("" + ID);

        // then
        verify(repository);
    }

    protected PersonEntity createPersonEntity() {
        return new PersonEntity(FIRST_NAME, LAST_NAME);
    }

    protected PersonEntity createPersistedPersonEntity() {
        PersonEntity person = createPersonEntity();
        person.setId(ID);
        return person;
    }

    protected PersonEntity createOtherPersistedPersonEntity() {
        PersonEntity person = new PersonEntity(OTHER_FIRST_NAME, OTHER_LAST_NAME);
        person.setId(ID);
        return person;
    }

    protected PersonResource createPersonResource() {
        PersonResource person = new PersonResource();
        person.setFirstName(FIRST_NAME);
        person.setLastName(LAST_NAME);
        return person;
    }

    protected PersonResource createOtherPersonResource() {
        PersonResource person = new PersonResource();
        person.setFirstName(OTHER_FIRST_NAME);
        person.setLastName(OTHER_LAST_NAME);
        return person;
    }

    protected void checkPerson(PersonResource person) {
        Assert.assertNotNull(person);
        Assert.assertEquals("" + ID, person.getId());
        Assert.assertEquals(FIRST_NAME, person.getFirstName());
        Assert.assertEquals(LAST_NAME, person.getLastName());
    }

    protected void checkOtherPerson(PersonResource person) {
        Assert.assertNotNull(person);
        Assert.assertEquals("" + ID, person.getId());
        Assert.assertEquals(OTHER_FIRST_NAME, person.getFirstName());
        Assert.assertEquals(OTHER_LAST_NAME, person.getLastName());
    }

    public static PersonEntity eqPersonEntity(PersonEntity in) {
        reportMatcher(new PersonEntityEquals(in));
        return null;
    }

}
