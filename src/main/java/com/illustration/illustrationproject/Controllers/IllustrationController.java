package com.illustration.illustrationproject.Controllers;

import com.illustration.illustrationproject.Services.IllustrationService;
import com.illustration.illustrationproject.models.Category;
import com.illustration.illustrationproject.models.Illustration;
import com.illustration.illustrationproject.models.Language;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/illustration")
public class IllustrationController {

    private final IllustrationService illustrationService;

    public IllustrationController(IllustrationService illustrationService) {
        this.illustrationService = illustrationService;
    }

    @PostMapping("/add/user/{id_user}")
    public ResponseEntity<?> addIllustration(
            @PathVariable("id_user") long userId,
            @RequestParam("emptyFile") MultipartFile emptyFile,
            @RequestParam("fullFile") MultipartFile fullFile,
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("language") Language language,
            @RequestParam("category") Category category) {
        try {
            Illustration illus = illustrationService.addIllustration(userId, emptyFile, fullFile, name, description, language, category);
            return ResponseEntity.ok(illus);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to save illustration files");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/add-with-new-language/{id_illus}/user/{id_user}")
    public ResponseEntity<?> addIllustrationWithNewLanguage(
            @PathVariable("id_user") long userId,
            @PathVariable("id_illus") long illustrationId,
            @RequestParam("fullFile") MultipartFile fullFile,
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("language") Language language
            ) {
        try {
            Illustration illus = illustrationService.addIllustrationWithNewLanguage(userId, illustrationId, fullFile, name,
                    description , language);
            return ResponseEntity.ok(illus);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to save illustration files");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllIllustrations() {
        try {
            List<Illustration> illustrations = illustrationService.getAll();
            return ResponseEntity.ok(illustrations);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteIllustration(@PathVariable long id) {
        try {
            illustrationService.delete(id);
            return ResponseEntity.ok(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/by-id/{id}")
    public ResponseEntity<?> getById(@PathVariable long id) {
        try {
            return ResponseEntity.ok(illustrationService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    // Add other endpoints as needed

}
