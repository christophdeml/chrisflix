package deml.chrisflix.Repository;

import deml.chrisflix.Domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {

}