package com.spintech.testtask.controller;

import com.spintech.testtask.service.UserService;
import com.spintech.testtask.service.tmdb.TmdbApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/tv")
public class ExternalTVController {

    @Autowired
    private UserService userService;

    @Autowired
    private TmdbApi tmdbApi;

    @PostMapping("/popular")
    public ResponseEntity<String> popular(@RequestParam String email, @RequestParam String password) {
        if (userService.findUser(email, password) == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(tmdbApi.getPopularTVShows());
    }
}
