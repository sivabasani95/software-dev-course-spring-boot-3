package com.example.springBoot2.controllers;

import com.example.springBoot2.models.Movie;
import com.example.springBoot2.repositories.MovieRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    /*private final List<Movie> movies = List.of(
        new Movie("The Shawshank Redemption", "Frank Darabont", 1994, 142),
        new Movie("The Godfather", "Francis Ford Coppola", 1972, 175),
        new Movie("The Dark Knight", "Christopher Nolan", 2008, 152)
    );*/

    private final MovieRepository movieRepository;
    public MovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }
// CEREATE  uparation [@PostMapping]
// Add multiple movies
@PostMapping("/all")
public List<Movie> addMovies(@RequestBody List<Movie> movies) {
    return movieRepository.saveAll(movies);
}
// read  by all oporation
    @GetMapping
    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }

    // read by id

    @GetMapping("/{id}")
    public Movie getMovieById(@PathVariable int id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found!"));
    }

// UPDATE oparation [@PutMapping] annotation
@PutMapping("/{id}")
public Movie updateMovie(@PathVariable int id, @RequestBody Movie updatedMovie) {
    Movie movie = movieRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Movie not found"));

    movie.setName(updatedMovie.getName());
    movie.setYear(updatedMovie.getYear());
    movie.setDirector(updatedMovie.getDirector());
    movie.setRuntime(updatedMovie.getRuntime());

    return movieRepository.save(movie);

}

// DELETE OPARATATION [@DeleteMapping] method

    @DeleteMapping("/{id}")
    public String deleteMovie(@PathVariable int id) {
        movieRepository.deleteById(id);
        return "Movie deleted successfully!";
    }

}


