package springcore.fastcamp.fastcamp.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import springcore.fastcamp.fastcamp.domin.ProductUser;
import springcore.fastcamp.fastcamp.repository.ProductUserRepository;
import springcore.fastcamp.fastcamp.service.userService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final ProductUserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ProductUser productUser = userRepository.findByname(username)
                .orElseThrow(() -> new UsernameNotFoundException("Can't not " + username));
        System.out.println(productUser.toString());
        return new UserDetailsImpl(productUser);
    }
}
