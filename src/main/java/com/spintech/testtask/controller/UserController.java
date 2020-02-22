package com.spintech.testtask.controller;

import com.spintech.testtask.entity.Actor;
import com.spintech.testtask.entity.TvShow;
import com.spintech.testtask.entity.User;
import com.spintech.testtask.service.ActorService;
import com.spintech.testtask.service.TvShowService;
import com.spintech.testtask.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ActorService actorService;

    @Autowired
    private TvShowService tvShowService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestParam String email, @RequestParam String password) {
        User user = userService.registerUser(email, password);
        return user != null ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }

    @PostMapping("/addFavouriteActor")
    public ResponseEntity<User> addFavouriteActor(@RequestParam String email, @RequestParam String password, @RequestParam String actorName) {
        Actor actor = actorService.findActor(actorName);
        User user = userService.findUser(email, password);
        if (actor == null || user == null) {
            return ResponseEntity.badRequest().build();
        }

        userService.addFavouriteActor(user, actor);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/deleteFavouriteActor")
    public ResponseEntity<User> deleteFavouriteActor(@RequestParam String email, @RequestParam String password, @RequestParam String actorName) {
        Actor actor = actorService.findActor(actorName);
        User user = userService.findUser(email, password);
        if (actor == null || user == null) {
            return ResponseEntity.badRequest().build();
        }

        userService.deleteFavouriteActor(user, actor);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/markTvShowAsWatched")
    public ResponseEntity<User> markTvShowAsWatched(@RequestParam String email, @RequestParam String password, @RequestParam String tvShow) {
        User user = userService.findUser(email, password);
        TvShow show = tvShowService.findTvShow(tvShow);
        if (user == null || show == null) {
            return ResponseEntity.badRequest().build();
        }

        userService.markTvShowAsWatched(user, show);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/unMarkTvShowAsWatched")
    public ResponseEntity<User> unMarkTvShowAsWatched(@RequestParam String email, @RequestParam String password, @RequestParam String tvShow) {
        User user = userService.findUser(email, password);
        TvShow show = tvShowService.findTvShow(tvShow);
        if (user == null || show == null) {
            return ResponseEntity.badRequest().build();
        }

        userService.unMarkTvShowAsWatched(user, show);
        return ResponseEntity.ok(user);
    }
}
