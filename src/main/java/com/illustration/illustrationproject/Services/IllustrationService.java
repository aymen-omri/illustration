package com.illustration.illustrationproject.Services;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.illustration.illustrationproject.models.Category;
import com.illustration.illustrationproject.models.Illustration;
import com.illustration.illustrationproject.models.Language;

public interface IllustrationService {

    Illustration addIllustration(
            long id_user,
            MultipartFile empty,
            MultipartFile full,
            String name,
            String description,
            Language language,
            Category category)
            throws IOException;

    Illustration addIllustrationWithNewLanguage(
            long id_user,
            long id_illus,
            MultipartFile full,
            String name,
            String description,
            Language language) throws IOException;

    List<Illustration> getAll();

    void delete(long id);

    Illustration findById(Long id);

}
