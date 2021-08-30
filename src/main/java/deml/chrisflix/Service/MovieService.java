package deml.chrisflix.Service;

import deml.chrisflix.Domain.Movie;
import deml.chrisflix.Repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(final MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Optional<Movie> findById(final long movieid) {
        return movieRepository.findById(movieid);
    }

    public List<Movie> findAll() {
        return movieRepository.findAll().stream().sorted(Comparator.comparing(m -> m.getTitle().toCharArray()[0])).collect(Collectors.toList());
    }

    public void updatePlayedTimeForMovie(final long movieId, final long playedTimeInSeconds) {
        Optional<Movie> optionalMovie = movieRepository.findById(movieId);
        if(optionalMovie.isPresent()) {
            Movie movie = optionalMovie.get();
            movie.setPlayedTimeInSeconds(playedTimeInSeconds);
            movieRepository.save(movie);
        }
    }

    public List<Movie> findAllActivated() {
        return movieRepository.findAll().stream().filter(Movie::isActivated).sorted(Comparator.comparing(m -> m.getTitle().toCharArray()[0])).collect(Collectors.toList());
    }
}
