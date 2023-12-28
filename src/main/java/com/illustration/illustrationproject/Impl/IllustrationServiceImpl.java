package com.illustration.illustrationproject.Impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.illustration.illustrationproject.Services.IllustrationService;
import com.illustration.illustrationproject.models.AddedIllustration;
import com.illustration.illustrationproject.models.Category;
import com.illustration.illustrationproject.models.Illustration;
import com.illustration.illustrationproject.models.Language;
import com.illustration.illustrationproject.models._User;
import com.illustration.illustrationproject.repositories.IllustrationRepo;
import com.illustration.illustrationproject.repositories.UserRepo;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class IllustrationServiceImpl implements IllustrationService {

    private final IllustrationRepo illustrationRepo;
    private final UserRepo userRepo;

    @Override
    public Illustration addIllustration(
            long id_user,
            MultipartFile empty,
            MultipartFile full,
            String name,
            String description,
            Language language,
            Category category) throws IOException {
        _User user = userRepo.findById(id_user)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Illustration illustration = new Illustration();
        illustration.setStatus(1);
        illustration.setUser(user);
        illustration.setDescription(description);
        illustration.setName(name);
        illustration.setCategory(category);
        illustration.setLanguage(language);
        illustration.setDate(LocalDate.now());
        illustration.setEmptyIllustrationUrl(saveFile(empty));
        illustration.setIllustrationUrl(saveFile(full));

        return illustrationRepo.save(illustration);
    }

    @Override
    public Illustration addIllustrationWithNewLanguage(long id_user, long id_illus, MultipartFile full,
            String name,
            String description,
            Language language) throws IOException {
        _User user = userRepo.findById(id_user)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Illustration existingIllustration = illustrationRepo.findById(id_illus)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        AddedIllustration illustration = new AddedIllustration();
        illustration.setUser(user);
        illustration.setDescription(description);
        illustration.setName(name);
        illustration.setDate(LocalDate.now());
        illustration.setLanguage(language);
        illustration.setIllustrationUrl(saveFile(full));

        existingIllustration.getAddedIlustrtions().add(illustration);
        ;
        return illustrationRepo.save(existingIllustration);
    }

    @Override
    public List<Illustration> getAll() {
        return illustrationRepo.findAll().stream()
                .filter(elem -> elem.getStatus() == 1).toList();
    }

    @Override
    @Transactional
    public void delete(long id) {
        Illustration existingIllustration = illustrationRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        existingIllustration.setStatus(0);
    }

    @Override
    public Illustration findById(Long id) {
        return illustrationRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("not found"));
    }

    private String saveFile(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename() + "_" + generateRandomString();
        String filePath = "src/main/resources/static/uploads/"
                + fileName;

        Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);

        return "http://localhost:8080/content/" + fileName;
    }

    private String generateRandomString() {
        return UUID.randomUUID().toString().substring(0, 20);
    }

}
