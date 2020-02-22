package com.spintech.testtask.controller;

import com.spintech.testtask.entity.TvShow;
import com.spintech.testtask.service.TvShowService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/tvShow")
public class TvShowController {

    @Autowired
    private TvShowService tvShowService;

    @PostMapping("/create")
    public ResponseEntity<TvShow> createTvShow(@RequestParam String name) {
        TvShow tvShow = tvShowService.createTvShow(name);
        return tvShow != null ? ResponseEntity.ok(tvShow) : ResponseEntity.badRequest().build();
    }

    @GetMapping("/find")
    public ResponseEntity<TvShow> getTvShowByName(@RequestParam String name) {
        return ResponseEntity.ok(tvShowService.findTvShow(name));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<TvShow> getTvShowById(@PathVariable Long id) {
        return ResponseEntity.ok(tvShowService.findTvShow(id));
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<TvShow>> getTvShows() {
        return ResponseEntity.ok(tvShowService.getAllTvShows());
    }
}
