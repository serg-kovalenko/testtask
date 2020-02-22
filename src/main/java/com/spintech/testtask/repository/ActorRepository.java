package com.spintech.testtask.repository;

import com.spintech.testtask.entity.Actor;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ActorRepository extends CrudRepository<Actor, Long> {

    Optional<Actor> findByName(String name);
}
