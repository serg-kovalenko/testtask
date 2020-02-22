package com.spintech.testtask.service.impl;

import com.spintech.testtask.entity.Actor;
import com.spintech.testtask.entity.TvShow;
import com.spintech.testtask.entity.User;
import com.spintech.testtask.repository.UserRepository;
import com.spintech.testtask.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public User registerUser(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            log.info("User with email [{}] is actually exist", email);
            return null;
        }
        user = User.builder().email(email).password(passwordEncoder.encode(password)).build();
        return userRepository.save(user);
    }

    @Override
    public User findUser(String email, String password) {
        User foundedUser = userRepository.findByEmail(email);
        if (foundedUser != null && passwordEncoder.matches(password, foundedUser.getPassword())) {
            return foundedUser;
        }
        log.info("User with email or password is incorrect");
        return null;
    }

    @Override
    public User addFavouriteActor(User user, Actor actor) {
        user.addFavouriteActor(actor);
        return userRepository.save(user);
    }

    @Override
    public User deleteFavouriteActor(User user, Actor actor) {
        user.removeFavouriteActor(actor);
        return userRepository.save(user);
    }

    @Override
    public User markTvShowAsWatched(User user, TvShow tvShow) {
        user.addWatchedTvShow(tvShow);
        return userRepository.save(user);
    }

    @Override
    public User unMarkTvShowAsWatched(User user, TvShow tvShow) {
        user.removeFavouriteTvShow(tvShow);
        return userRepository.save(user);
    }
}