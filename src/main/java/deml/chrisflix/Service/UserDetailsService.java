package deml.chrisflix.Service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        if ("chrisflix".equals(s)) {
            return new deml.chrisflix.Domain.UserDetails(true);
        } else {
            return new deml.chrisflix.Domain.UserDetails(false);
        }
    }
}
