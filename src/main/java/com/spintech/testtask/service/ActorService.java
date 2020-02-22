package com.spintech.testtask.service;

import com.spintech.testtask.entity.Actor;

import java.util.List;

public interface ActorService {
    Actor createActor(String name);

    Actor findActor(Long id);

    Actor findActor(String name);

    List<Actor> getAllActors();
}
