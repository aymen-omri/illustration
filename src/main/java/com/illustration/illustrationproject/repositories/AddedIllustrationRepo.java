package com.illustration.illustrationproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.illustration.illustrationproject.models.AddedIllustration;

public interface AddedIllustrationRepo extends JpaRepository<AddedIllustration , Long> {
    
}
