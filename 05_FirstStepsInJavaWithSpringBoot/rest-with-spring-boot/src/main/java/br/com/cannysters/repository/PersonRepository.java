package br.com.cannysters.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cannysters.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{}
