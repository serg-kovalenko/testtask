package com.spintech.testtask.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String email;

    private String password;

    @OneToMany
    private Set<Actor> favouriteActors = new HashSet<>();

    @OneToMany
    private Set<TvShow> watchedTvShows = new HashSet<>();

    public void addFavouriteActor(Actor actor) {
        favouriteActors.add(actor);
    }

    public void removeFavouriteActor(Actor actor) {
        favouriteActors.remove(actor);
    }

    public void addWatchedTvShow(TvShow show) {
        watchedTvShows.add(show);
    }

    public void removeFavouriteTvShow(TvShow show) {
        watchedTvShows.remove(show);
    }
}