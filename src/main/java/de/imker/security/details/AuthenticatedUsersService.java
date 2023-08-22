package de.imker.security.details;

import de.imker.repositories.UsersRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AuthenticatedUsersService implements UserDetailsService {

  private final UsersRepository usersRepository;
  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return new AuthenticatedUser(
        usersRepository.findByEmail(email)
            .orElseThrow(() ->
                new UsernameNotFoundException("User with email <" + email + "> not found ")));
  }
}
