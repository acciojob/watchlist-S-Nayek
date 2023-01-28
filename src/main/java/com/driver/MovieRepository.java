package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MovieRepository {

    private HashMap<String,Movie> movieMap;
    private HashMap<String,Director> directorMap;
    private HashMap<String,List<String>> DirectorMovieMap;

    public MovieRepository() {
        this.movieMap=new HashMap<>();
        this.directorMap=new HashMap<>();
        this.DirectorMovieMap =new HashMap<>();
    }

    public void addMovie(Movie movie){
        movieMap.put(movie.getName(),movie);
    }

    public void addDirector( Director director){
        directorMap.put(director.getName(),director);
    }



    public Director findDirector(String directorName) {
        return directorMap.get(directorName);
    }

    public void saveMovieDirectorPair(String movie, String director) {
        if(movieMap.containsKey(movie) && directorMap.containsKey(director)){
//            movieMap.put(movie,movieMap.get(movie));
//            directorMap.put(director,directorMap.get(director));
            List<String> currentMovies =new ArrayList<>();
            if(DirectorMovieMap.containsKey(director))
                currentMovies= DirectorMovieMap.get(director);

            currentMovies.add(movie);
            DirectorMovieMap.put(director,currentMovies);
        }
    }

    public Movie findMovie(String movieName) {
        return movieMap.get(movieName);
    }

    public List<String> findMovieByDirector(String director) {
        List<String> movieList =new ArrayList<>();
        if(DirectorMovieMap.containsKey(director))
            movieList=DirectorMovieMap.get(director);

        return movieList;
    }

    public List<String> getAllMovies() {
       return new ArrayList<>(movieMap.keySet());

    }

    public void deleteDirector(String director) {
        List<String> movies =new ArrayList<>();
        if(DirectorMovieMap.containsKey(director)) {
            movies = DirectorMovieMap.get(director);
            for (String movie : movies) {
                if (movieMap.containsKey(movie))
                    movieMap.remove(movie);

            }
            DirectorMovieMap.remove(director);

        }
        if(directorMap.containsKey(director))
            directorMap.remove(director);

    }

    public void deleteAllDirector() {
        HashSet<String> movielist =new HashSet<>();
        for(String director : DirectorMovieMap.keySet()){
            for(String movie: DirectorMovieMap.get(director)){
                movielist.add(movie);
            }
        }
        for(String movie: movielist){
            if(movieMap.containsKey(movie))
                movieMap.remove(movie);
        }
    }

    public String getDirectorNameFromMovie(String movie) {
        HashSet<String> movieSet =new HashSet<>();
        for(String director: DirectorMovieMap.keySet()){
            if(DirectorMovieMap.get(director).contains(movie))
                return director;
        }
        return "no such director found";
    }
}
