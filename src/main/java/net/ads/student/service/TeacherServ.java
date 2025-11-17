package net.ads.student.service;

import net.ads.student.dto.TeacherRegis;
import net.ads.student.model.Teachers;
import net.ads.student.repo.TeacherRepo;
import org.apache.catalina.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class TeacherServ implements UserDetailsService {

    @Autowired
    private TeacherRepo teacherrepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public Teachers save(TeacherRegis registration) {

        if (teacherrepo.findByEmail(registration.getEmail()) != null) {
            throw new IllegalArgumentException("Email already registered!");
        }

        Teachers teach = new Teachers(
                registration.getFirstName(),
                registration.getLastName(),
                registration.getEmail(),
                passwordEncoder.encode(registration.getPassword()),  // ✅ encode once
                Arrays.asList(new Role("ROLE_USER"))
        );
        return TeacherRepo.save(teach);
    }

    @Override  // ✅ this annotation ensures correct method override
    public UserDetails loadUserByTeachername(String username) throws UsernameNotFoundException {
        Teachers teach = TeacherRepo.findByEmail(username);
        if (teach == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }

        return new org.springframework.security.core.userdetails.User(
                teach.getEmail(),
                teach.getPassword(),
                mapRolesToAuthorities(user.getRoles())
        );
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
