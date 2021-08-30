package deml.chrisflix.Controller;

import deml.chrisflix.Domain.*;
import deml.chrisflix.Util.*;
import org.apache.catalina.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.*;
import java.util.*;

@Controller
public class UserManagerController {

    @GetMapping("/resetexecutioncount")
    @CrossOrigin
    public ResponseEntity<String> resetExecutionCount(final HttpServletRequest request) {
        Optional<Cookie> optionalCookie = Arrays.stream(request.getCookies()).filter(c -> c.getName().equals("JSESSIONID")).findFirst();
        optionalCookie.ifPresent(cookie -> {
            UserAgent userAgent = UserManager.getInstance().getUserAgentById(cookie.getValue());
            if(userAgent == null) {
                updateUserAgent(request);
                userAgent = UserManager.getInstance().getUserAgentById(cookie.getValue());
            }
            userAgent.resetExecutionCount();
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/hasmessage")
    @CrossOrigin
    public ResponseEntity<String> getHasMessage(HttpServletRequest request) {
        Optional<Cookie> optionalCookie = Arrays.stream(request.getCookies()).filter(c -> c.getName().equals("JSESSIONID")).findFirst();
        String command;
        if(optionalCookie.isPresent() && UserManager.getInstance().getUserAgentById(optionalCookie.get().getValue()) != null) {
            String movieid = UserManager.getInstance().getMovieIdForUseragentId(optionalCookie.get().getValue());
            command = "play";
            if(movieid != null) {
                return new ResponseEntity<>("{\"command\": \"" + command + "\", \"movieid\": " + movieid + "} ", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("{null}", HttpStatus.NO_CONTENT);
            }
        }
        return new ResponseEntity<>("{null}", HttpStatus.NO_CONTENT);
    }

    @GetMapping("/setname")
    @CrossOrigin
    public ResponseEntity<String> setUserAgentName(final HttpServletRequest request, @RequestParam("name") final String name) {
        Logger.INFO("Renaming UserAgent '" + UserManager.getInstance().getCurrentUserAgent(request).getUsername() + "' to '" + name + "'");
        Optional<Cookie> optionalCookie = Arrays.stream(request.getCookies()).filter(c -> c.getName().equals("JSESSIONID")).findFirst();
        optionalCookie.ifPresent(cookie -> UserManager.getInstance().setUserAgentName(cookie.getValue(), name.replaceAll("%20", " ")));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/postmessage")
    @CrossOrigin
    public ResponseEntity<String> postMessage(final HttpServletRequest request, @RequestParam("useragentId") final String useragentId, final @RequestParam("movieId") Long movieId) {
        UserManager.getInstance().addMessage(useragentId, movieId.toString());
        return new ResponseEntity<>(HttpStatus.OK);
    }


    public static void updateUserAgent(final HttpServletRequest request) {
        Optional<Cookie> optionalCookie = Arrays.stream(request.getCookies()).filter(c -> c.getName().equals(Cookies.JSESSIONID)).findFirst();
        Optional<Cookie> optionalCookieName = Arrays.stream(request.getCookies()).filter(c -> c.getName().equals(Cookies.USERAGENTNAME)).findFirst();

        optionalCookie.ifPresent(cookie -> {
            String userAgentName;
            userAgentName = optionalCookieName.map(value -> value.getValue().replaceAll("%20", " ")).orElseGet(cookie::getValue);
            if(!UserManager.getInstance().existsAgent(cookie.getValue())) {
                UserManager.getInstance().addAgent(UserAgent.create(cookie.getValue(), userAgentName, request.getHeader("user-agent")));
                Logger.INFO("Adding new UserAgent " + userAgentName);
            }
        });
    }
}
