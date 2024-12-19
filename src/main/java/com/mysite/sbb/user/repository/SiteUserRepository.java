package com.mysite.sbb.user.repository;

import com.mysite.sbb.user.entity.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SiteUserRepository extends JpaRepository<SiteUser, Long> {
}
