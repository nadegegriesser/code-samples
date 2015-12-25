package de.griesser.persistence.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import de.griesser.persistence.entities.PersonEntity;

@Transactional
@Repository
public interface PersonRepository extends CrudRepository<PersonEntity, Long> {
}
