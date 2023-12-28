package com.illustration.illustrationproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.illustration.illustrationproject.models.Illustration;

public interface IllustrationRepo extends JpaRepository<Illustration, Long> {

}
