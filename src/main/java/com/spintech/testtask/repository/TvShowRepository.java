package com.spintech.testtask.repository;

import com.spintech.testtask.entity.TvShow;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TvShowRepository extends CrudRepository<TvShow, Long> {
    Optional<TvShow> findByName(String name);
}
