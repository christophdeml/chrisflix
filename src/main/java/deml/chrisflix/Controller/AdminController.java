package deml.chrisflix.Controller;

import deml.chrisflix.Domain.*;
import deml.chrisflix.Service.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.*;
import java.util.Optional;

@Controller
public class AdminController {

    private final MovieService movieService;

    public AdminController(final MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/admin")
    public String getAdmin(final Model model, final HttpServletRequest request) {
        model.addAttribute("movies", movieService.findAll());
        model.addAttribute("useragent", UserManager.getInstance().getCurrentUserAgent(request));
        return "admin/admin";
    }

    @GetMapping("/admin/movie")
    public String getEditMovie(final Model model, final @RequestParam("movieid") long movieid) {
        movieService.findById(movieid).ifPresentOrElse(movie -> model.addAttribute("movie", movie), () -> model.addAttribute("error"));
        return "admin/movie";
    }
}
