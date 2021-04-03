package com.example.springdatarestprojection;

import org.springframework.data.repository.CrudRepository;

public interface PersonRepos extends CrudRepository<Person, Long> {
}
