package com.gary.cloudinteractive.webapi.security.service;

import com.gary.cloudinteractive.webapi.security.JwtUserFactory;
import com.gary.cloudinteractive.webapi.security.model.Authority;
import com.gary.cloudinteractive.webapi.security.model.AuthorityName;
import com.gary.cloudinteractive.webapi.security.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Value("${securityUser}")
    private String securityUser;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 這邊做取得設定User資料及Authorities
        List<Authority> authorities = new ArrayList<>();
        authorities.add(new Authority(AuthorityName.ROLE_USER));
        User user = new User(securityUser, new BCryptPasswordEncoder().encode(securityUser), authorities);

        if (!securityUser.equals(username)) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return JwtUserFactory.create(user);
        }
    }
}
