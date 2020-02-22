package com.spintech.testtask.service;

import com.spintech.testtask.entity.TvShow;

import java.util.List;

public interface TvShowService {
    TvShow createTvShow(String name);

    TvShow findTvShow(Long id);

    TvShow findTvShow(String name);

    List<TvShow> getAllTvShows();
}
