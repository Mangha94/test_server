package com.test.lee.project.jwt;

import com.test.lee.project.model.member.MemberSv;
import com.test.lee.project.model.member.data.Member;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

@Service ("jwtUserDetailsService")
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    final private MemberSv memberSv;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return new User(username, "",
                new ArrayList<>());
    }
}
