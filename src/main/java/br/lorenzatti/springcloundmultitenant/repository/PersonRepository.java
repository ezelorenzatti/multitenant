package br.lorenzatti.springcloundmultitenant.repository;

import br.lorenzatti.springcloundmultitenant.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

    static Person named(String name) {
        Person person = new Person();
        person.setName(name);
        return person;
    }
}
