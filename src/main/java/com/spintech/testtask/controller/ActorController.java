package com.spintech.testtask.controller;

import com.spintech.testtask.entity.Actor;
import com.spintech.testtask.service.ActorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/actor")
public class ActorController {

    @Autowired
    private ActorService actorService;

    @PostMapping("/create")
    public ResponseEntity<Actor> createActor(@RequestParam String name) {
        Actor actor = actorService.createActor(name);
        return actor != null ? ResponseEntity.ok(actor) : ResponseEntity.badRequest().build();
    }

    @GetMapping("/find")
    public ResponseEntity<Actor> getActorByName(@RequestParam String name) {
        return ResponseEntity.ok(actorService.findActor(name));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Actor> getActorById(@PathVariable Long id) {
        return ResponseEntity.ok(actorService.findActor(id));
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Actor>> getActors() {
        return ResponseEntity.ok(actorService.getAllActors());
    }
}
