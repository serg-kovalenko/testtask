package com.spintech.testtask.service.impl;

import com.spintech.testtask.entity.TvShow;
import com.spintech.testtask.repository.TvShowRepository;
import com.spintech.testtask.service.TvShowService;
import com.spintech.testtask.utils.IteratorUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TvShowServiceImpl implements TvShowService {

    @Autowired
    private TvShowRepository repository;

    @Override
    public TvShow createTvShow(String name) {
        if (repository.findByName(name).isPresent()) {
            log.info("TvShow [{}] is already exist", name);
        }
        TvShow tvShow = TvShow.builder().name(name.toLowerCase()).build();
        return repository.save(tvShow);
    }

    @Override
    public TvShow findTvShow(Long id) {
        Optional<TvShow> tvShow = repository.findById(id);
        if (!tvShow.isPresent()) {
            log.warn("TvShow [{}] was not found", id);
            return null;
        }
        return tvShow.get();
    }

    @Override
    public TvShow findTvShow(String name) {
        Optional<TvShow> tvShow = repository.findByName(name);
        if (!tvShow.isPresent()) {
            log.warn("TvShow [{}] was not found", name);
            return null;
        }
        return tvShow.get();
    }

    @Override
    public List<TvShow> getAllTvShows() {
        return IteratorUtils.toList(repository.findAll().iterator());
    }
}
