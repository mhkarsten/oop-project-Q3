package server.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import server.model.User;
import server.repository.UserRepository;

import java.util.Optional;

/**
 * Manages the authentication and registration of users.
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Used for the authentication of users (Checks for the password implicitly).
     *
     * @param username the username of the user that has to be authenticated
     * @return return returns the userDetails if successful
     * @throws UsernameNotFoundException throws UsernameNotFoundException if the user name is not found
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByName(username);

        if (!user.isPresent()) {
            throw new UsernameNotFoundException(username);
        }

        return new MyUserPrincipal(user.get());
    }

    /**
     * Checks if the username is already in use.
     * @param user the User object to check with
     * @return a boolean whether or not the Username has been found
     */
    public boolean userExists(User user) {
        return userRepository.findByName(user.getName()).isPresent();
    }

    /**
     * Checks if the username is still available, encodes the password and persists the entity.
     * @param user the User object to check with
     * @return User return the
     */
    public User registerNewUserAccount(User user) {
        if (userExists(user)) {
            return null;
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

}
