package deml.chrisflix.Controller;

import deml.chrisflix.Service.MovieService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class MovieRestController {

    private final MovieService movieService;

    public MovieRestController(final MovieService movieService) {
        this.movieService = movieService;
    }

    @CrossOrigin("*")
    @GetMapping("/api/movie/play/time")
    public void putMoviePlayTime(@RequestParam("movieId") final long movieId, @RequestParam("playedTime") final long playedTimeInSeconds) {
        movieService.updatePlayedTimeForMovie(movieId, playedTimeInSeconds);
    }
}
