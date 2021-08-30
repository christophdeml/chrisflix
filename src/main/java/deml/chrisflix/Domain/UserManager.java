package deml.chrisflix.Domain;

import deml.chrisflix.Util.*;

import javax.servlet.http.*;
import java.util.*;
import java.util.stream.*;

public class UserManager {

    private static final UserManager instance = new UserManager();
    private final List<UserAgent> userAgents = new ArrayList<>();
    private final Map<String, String> messages = new HashMap<>();

    private UserManager(){
        new Thread(() -> {
            try {
                manageUserAgents();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void manageUserAgents() throws InterruptedException {
        while(true) {
            Iterator<UserAgent> iterator = userAgents.iterator();
            while (iterator.hasNext()) {
                UserAgent userAgent = iterator.next();
                userAgent.incrementExecutionCount();
                if (userAgent.getExecutionCount() > 15) {
                    Logger.INFO("Removing UserAgent " + userAgent.toString() + " due to expiration");
                    iterator.remove();
                }
            }
            Thread.sleep(1000, 0);
        }
    }

    public synchronized static UserManager getInstance() {
        return instance;
    }

    public boolean existsAgent(final String id) {
        return userAgents.stream().anyMatch(ua -> ua.getId().equals(id));
    }

    public void addAgent(final UserAgent agent) {
        userAgents.add(agent);
    }

    public List<UserAgent> getUserAgents() {
        return userAgents;
    }

    public UserAgent getUserAgentById(final String userAgentId) {
        return userAgents.stream().filter(userAgent -> userAgent.getId().equals(userAgentId)).findAny().orElse(null);
    }

    public void addMessage(final String userAgentId, final String message) {
        messages.put(userAgentId, message);
    }

    public String getMovieIdForUseragentId(final String userAgentId) {
        String movieid = messages.get(userAgentId);
        messages.remove(userAgentId);
        return movieid;
    }

    public void setUserAgentName(final String userAgentId, final String userAgentName) {
        for (UserAgent userAgent : userAgents) {
            if (userAgent.getId().equals(userAgentId)) {
                userAgent.setUsername(userAgentName);
            }
        }
    }

    public UserAgent getCurrentUserAgent(final HttpServletRequest request) {
        Optional<Cookie> optionalCookie = Arrays.stream(request.getCookies()).filter(c -> c.getName().equals("JSESSIONID")).findFirst();
        return optionalCookie.flatMap(cookie -> userAgents.stream().filter(u -> u.getId().equals(cookie.getValue())).findFirst()).orElse(null);
    }

    public List<UserAgent> getAllOtherUserAgents(final HttpServletRequest request) {
        return getUserAgents().stream().filter(ua -> !ua.getId().equals(getCurrentUserAgent(request).getId())).collect(Collectors.toList());
    }
}
