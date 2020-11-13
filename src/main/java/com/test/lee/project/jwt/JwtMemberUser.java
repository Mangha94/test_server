package com.test.lee.project.jwt;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;

class JwtMemberUser extends User {

    JwtMemberUser(String id, List<GrantedAuthority> authorities) {
        super(id, "", authorities);
    }

    JwtMemberUser(JwtRequest member, Collection<? extends GrantedAuthority> authorities) {
        super(member.getUsername(), member.getPassword(), authorities);
    }
}
