package de.griesser.rest.services.integration;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import de.griesser.rest.exceptions.PersonNotFound;
import de.griesser.rest.resources.PersonResource;
import de.griesser.rest.services.PersonService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-client.xml")
@TestExecutionListeners(listeners = { DependencyInjectionTestExecutionListener.class })
public class PersonServiceTest {

    private static final String LAST_NAME = "Griesser";
    private static final String FIRST_NAME = "Nadege";
    @Autowired
    private PersonService sut;

    @Test
    public void test() throws PersonNotFound {
        testInitialGetAll();

        String id = testCreate();

        testGetAllAfterCreate(id);

        testGetAfterCreate(id);

        testDelete(id);
    }

    protected void testInitialGetAll() {
        // given

        // when
        Collection<PersonResource> res = sut.getAll();

        // then
        Assert.assertNotNull(res);
        Assert.assertTrue(res.isEmpty());
    }

    protected String testCreate() {
        // given
        PersonResource person = new PersonResource();
        person.setFirstName(FIRST_NAME);
        person.setLastName(LAST_NAME);

        // when
        person = sut.create(person);

        // then
        Assert.assertNotNull(person);
        Assert.assertNotNull(person.getId());
        Assert.assertEquals(FIRST_NAME, person.getFirstName());
        Assert.assertEquals(LAST_NAME, person.getLastName());
        return person.getId();
    }

    protected void testGetAllAfterCreate(String id) {
        // given

        // when
        Collection<PersonResource> res = sut.getAll();

        // then
        Assert.assertNotNull(res);
        Assert.assertEquals(1, res.size());
        PersonResource person = res.iterator().next();
        checkPerson(person, id);
    }

    protected void testGetAfterCreate(String id) throws PersonNotFound {
        // given

        // when
        PersonResource person = sut.get(id);

        // then
        checkPerson(person, id);
    }

    protected void testDelete(String id) throws PersonNotFound {
        // given

        // when
        sut.delete(id);

        // then
    }

    protected void checkPerson(PersonResource person, String id) {
        Assert.assertNotNull(person);
        Assert.assertEquals(id, person.getId());
        Assert.assertEquals(FIRST_NAME, person.getFirstName());
        Assert.assertEquals(LAST_NAME, person.getLastName());
    }
}
