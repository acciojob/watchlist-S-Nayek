package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MovieController {
    @Autowired
    MovieService movieServices;

    @PostMapping("/add-movie")
    public ResponseEntity<String> addMovie(@RequestBody Movie movie){
        movieServices.addMovie(movie);
        return new ResponseEntity<>("new movie added successfully", HttpStatus.CREATED);
    }
    @PostMapping("/add-director")
    public ResponseEntity<String> addDirector(@RequestBody Director director){
        movieServices.addDirector(director);
        return new ResponseEntity<>("new director added successfully",HttpStatus.CREATED);
    }
    @PostMapping("/add-movie-director-pair")
    public ResponseEntity<String> addMovieDirectorPair(@RequestParam("movie") String movie,@RequestParam("director") String director){
        movieServices.createMovieDirectorpair(movie,director);
        return new ResponseEntity<>("new movie-director pair added successfully",HttpStatus.CREATED);
    }
    @GetMapping("/get-movie-by-name/{name}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable("q") String name){
        Movie movies = movieServices.findMovie(name);
        return new ResponseEntity<>(movies,HttpStatus.CREATED);
    }
    @GetMapping("/get-director-by-name/{name}")
    public ResponseEntity<Director> getDirectorByName(@PathVariable("q") String name){
        Director directors=movieServices.findDirector(name);
        return new ResponseEntity<>(directors,HttpStatus.CREATED);
    }
    @GetMapping("/get-movies-by-director-name/{director}")
    public ResponseEntity<List<String>> getMoviesByDirectorName(@PathVariable String director){
        List<String> movies =movieServices.findMovieByDirector(director);
        return new ResponseEntity<>(movies,HttpStatus.CREATED);

    }
    @GetMapping("/get-all-movies")
    public ResponseEntity<List<String>> findAllMovies(){
        List<String> movies =movieServices.getAllMovies();
        return new ResponseEntity<>(movies,HttpStatus.CREATED);
    }
    @DeleteMapping("/delete-director-by-name")
    public ResponseEntity<String> deleteDirectorByName(@RequestParam("q") String director) {
        movieServices.deleteDirector(director);
        return new ResponseEntity<>(director+" removed successfully ",HttpStatus.CREATED);
    }
    @DeleteMapping("/delete-all-director")
    public ResponseEntity<String> deleteAllDirectors(){
        movieServices.deleteAllDirector();
        return new ResponseEntity<>("all director removed successfully",HttpStatus.CREATED);
    }
    @GetMapping("/get-director-name-form-movie")
    public String getDirectorName(@RequestParam String movie){
        return movieServices.getDirectorName(movie);
    }


}
