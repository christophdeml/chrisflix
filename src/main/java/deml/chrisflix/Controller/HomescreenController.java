package deml.chrisflix.Controller;

import deml.chrisflix.*;
import deml.chrisflix.Domain.*;
import deml.chrisflix.Service.MovieService;
import deml.chrisflix.Util.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.*;
import java.util.*;

@Controller
public class HomescreenController {

    private final MovieService movieService;

    public HomescreenController(final MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/")
    public String getHomescreen(final Model model, final HttpServletRequest request) {
        UserManagerController.updateUserAgent(request);
        String userAgent = request.getHeader("User-Agent");
        String imageSize;
        if(stringContainsAny(userAgent, "Android", "Mobile", "IPhone")) {
            imageSize = "small/";
        }  else {
            imageSize = "";
        }
        model.addAttribute("imageSize", imageSize);
        model.addAttribute("movies", movieService.findAllActivated());
        Logger.INFO("Serving Homescreen to " + UserManager.getInstance().getCurrentUserAgent(request).getUsername());
        return "homescreen";
    }


    private boolean stringContainsAny(final String container, String ... containing) {
        boolean contains = false;
        for (String s : containing
             ) {
            if (container.contains(s)) {
                contains = true;
                break;
            }
        }
        return contains;
    }
}
