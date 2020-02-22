package com.spintech.testtask.service;

import com.spintech.testtask.entity.Actor;
import com.spintech.testtask.entity.TvShow;
import com.spintech.testtask.entity.User;

public interface UserService {
    User registerUser(String email, String password);

    User findUser(String email, String password);

    User addFavouriteActor(User user, Actor actor);

    User deleteFavouriteActor(User user, Actor actor);

    User markTvShowAsWatched(User user, TvShow tvShow);

    User unMarkTvShowAsWatched(User user, TvShow tvShow);
}

