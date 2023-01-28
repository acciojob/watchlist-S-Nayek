package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MovieService {
@Autowired
MovieRepository movieRepository;

    public Director findDirector(String directorName) {
        return movieRepository.findDirector(directorName);
    }


    public void createMovieDirectorpair(String movie, String director) {
        movieRepository.saveMovieDirectorPair(movie,director);
    }

    public void addMovie(Movie movie) {
        movieRepository.addMovie(movie);
    }

    public void addDirector(Director director) {
        movieRepository.addDirector(director);
    }

    public Movie findMovie(String movieName) {
        return movieRepository.findMovie(movieName);
    }

    public List<String> findMovieByDirector(String director) {
        return movieRepository.findMovieByDirector(director);
    }

    public List<String> getAllMovies() {
        return movieRepository.getAllMovies();
    }

    public void deleteDirector(String director) {
        movieRepository.deleteDirector(director);
    }

    public void deleteAllDirector() {
        movieRepository.deleteAllDirector();
    }

    public String getDirectorName(String movie) {
        return movieRepository.getDirectorNameFromMovie(movie);
    }
}
