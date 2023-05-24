package ch.bbw.m183.vulnerapp.provider;

import ch.bbw.m183.vulnerapp.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString(); //TODO: implement password checker

        UserDetails user = userDetailsService.loadUserByUsername(name);
        if (user == null || !new BCryptPasswordEncoder().matches(password, user.getPassword())) {
            throw new BadCredentialsException("invalid_username_or_pass");
        }

        // use the credentials
        // and authenticate against the third-party system
        return new UsernamePasswordAuthenticationToken(
                user.getUsername(), user.getPassword(), new ArrayList<>());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
