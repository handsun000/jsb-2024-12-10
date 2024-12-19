package com.mysite.sbb.user.service;

import com.mysite.sbb.user.entity.SiteUser;
import com.mysite.sbb.user.repository.SiteUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SiteUserService {
    private final SiteUserRepository siteUserRepository;
    private final PasswordEncoder passwordEncoder;

    public SiteUser create(String username, String password, String email) {
        SiteUser siteUser = SiteUser.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .email(email)
                .build();

        siteUserRepository.save(siteUser);

        return siteUser;
    }
}
