package ru.akorsa.springdata.jpa.repository;

import org.springframework.data.repository.CrudRepository;
import ru.akorsa.springdata.jpa.model.Hobby;

public interface HobbyRepository extends CrudRepository<Hobby, Long> {

    Hobby findByHobbyTitleIgnoreCase(String hobbyTitle);

}
