package springcore.fastcamp.fastcamp.security;

import lombok.RequiredArgsConstructor;
import  org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import springcore.fastcamp.fastcamp.domin.ProductUser;
import springcore.fastcamp.fastcamp.domin.User;
import springcore.fastcamp.fastcamp.domin.UserRole;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class UserDetailsImpl implements UserDetails {

    private final ProductUser user;

    public UserDetailsImpl(ProductUser productUser) {
        this.user = productUser;
    }

    private static final String ROLE_PREFIX = "ROLE_";

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        UserRole role = user.getRole();
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(ROLE_PREFIX + user.toString());
        Collection<GrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        simpleGrantedAuthorities.add(simpleGrantedAuthority);
        return simpleGrantedAuthorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getName();
    }

    public ProductUser getUser(){
        return user;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
