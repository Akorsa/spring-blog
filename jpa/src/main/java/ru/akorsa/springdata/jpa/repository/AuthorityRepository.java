package ru.akorsa.springdata.jpa.repository;


import org.springframework.data.repository.CrudRepository;
import ru.akorsa.springdata.jpa.model.Authority;

public interface AuthorityRepository extends CrudRepository<Authority, Long> {

    Authority findByAuthority(String authority);
}
