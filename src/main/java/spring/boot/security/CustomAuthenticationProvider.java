package spring.boot.security;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import spring.boot.user.UserConverter;
import spring.boot.user.UserDto;
import spring.boot.user.UserRepository;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private final PasswordEncoder pEncoder;
    @Autowired
    private final UserRepository repository;
    @Autowired
    private final UserConverter converter;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        List<UserDto> users = converter.fromList(repository.findByEmail(username));
        if (users.size() > 0) {
            if (pEncoder.matches(password, users.get(0).getPassword())) {
                List<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority(users.get(0).getRole().getName()));
                return new UsernamePasswordAuthenticationToken(username, password, authorities);
            }else  {
                throw new BadCredentialsException("Invalid password");
            }
        } else {
            throw new BadCredentialsException("No user registered with this details");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
