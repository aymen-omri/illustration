package com.illustration.illustrationproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.illustration.illustrationproject.models._Token;

public interface TokenRepo extends JpaRepository<_Token, Long> {

    @Query("select t from _Token t where t.user.email = ?1")
    _Token findByUserEmail(String email);

}
