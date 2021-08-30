package deml.chrisflix.Controller;

import deml.chrisflix.Domain.*;
import deml.chrisflix.Service.MovieService;
import deml.chrisflix.Util.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.*;
import java.util.Map;
import java.util.Optional;

@Controller
public class MovieController {

    private final MovieService movieService;

    public MovieController(final MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movie")
    public String getMovie(final Model model, final HttpServletRequest request, final @RequestParam("movieid") Long movieid) {
        Optional<Movie> optionalMovie = movieService.findById(movieid);
        if (optionalMovie.isPresent()) {
            model.addAttribute("movie", optionalMovie.get());
            model.addAttribute("userAgent", UserManager.getInstance().getCurrentUserAgent(request));
            model.addAttribute("userAgents", UserManager.getInstance().getAllOtherUserAgents(request));
            Logger.INFO("Serving Movie '" + optionalMovie.get().getTitle() + "'(" + optionalMovie.get().getYear() + ") to " + UserManager.getInstance().getCurrentUserAgent(request).getUsername());
            return "movie";
        }
        return "error";
    }

    //@GetMapping("/movie/play")
    public String getPlayMovie(final Model model, final @RequestParam("movieid") Long movieid) {
        Optional<Movie> optionalMovie = movieService.findById(movieid);
        if (optionalMovie.isPresent()) {
            model.addAttribute("movie", optionalMovie.get());
            return "play";
        }
        return "error";
    }

}
