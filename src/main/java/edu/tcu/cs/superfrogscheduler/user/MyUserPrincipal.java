package edu.tcu.cs.superfrogscheduler.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Collection;

public class MyUserPrincipal implements UserDetails {

    private SchedulerUser schedulerUser;


    public MyUserPrincipal(SchedulerUser schedulerUser) {
        this.schedulerUser = schedulerUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return Arrays.stream(StringUtils.tokenizeToStringArray(this.schedulerUser.getRoles(), " "))
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .toList();
    }

    @Override
    public String getPassword() {
        return this.schedulerUser.getPassword();
    }

    @Override
    public String getUsername() {
        return this.schedulerUser.getUsername();
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
        return this.schedulerUser.isEnabled();
    }

    public SchedulerUser getUser() {
        return schedulerUser;
    }

}
