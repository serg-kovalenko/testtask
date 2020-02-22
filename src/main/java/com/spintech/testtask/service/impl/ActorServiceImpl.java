package com.spintech.testtask.service.impl;

import com.spintech.testtask.entity.Actor;
import com.spintech.testtask.repository.ActorRepository;
import com.spintech.testtask.service.ActorService;
import com.spintech.testtask.utils.IteratorUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ActorServiceImpl implements ActorService {

    @Autowired
    private ActorRepository repository;

    @Override
    public Actor createActor(String name) {
        if (repository.findByName(name).isPresent()) {
            log.info("Actor [{}] is already exist", name);
            return null;
        }
        Actor actor = Actor.builder().name(name.toLowerCase()).build();
        return repository.save(actor);
    }

    @Override
    public Actor findActor(Long id) {
        Optional<Actor> actor = repository.findById(id);
        if (!actor.isPresent()) {
            log.warn("Actor [{}] was not found.", id);
            return null;
        }
        return actor.get();
    }

    @Override
    public Actor findActor(String name) {
        Optional<Actor> actor = repository.findByName(name.toLowerCase());
        if (!actor.isPresent()) {
            log.warn("Actor [{}] was not found.", name);
            return null;
        }
        return actor.get();
    }

    @Override
    public List<Actor> getAllActors() {
        return IteratorUtils.toList(repository.findAll().iterator());
    }
}
