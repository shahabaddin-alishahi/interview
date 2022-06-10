package com.signicat.interview.config.security;

import com.signicat.interview.domain.Authority;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class OnlineUserImpl implements OnlineUser {

    private Long userId;
    private String username;
    private String password;
    private String title;
    private Collection<Authority> authorities;

    public OnlineUserImpl(long userId, String username, String password, String title, Set<Authority> authorities) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.title = title;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        authorities.forEach(
                authority -> grantedAuthorities.add(new SimpleGrantedAuthority(authority.getAuthority()))
        );

        return grantedAuthorities;
    }

    @Override
    public Long getId() {
        return userId;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
