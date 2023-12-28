package com.illustration.illustrationproject.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AddedIllustration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_added;
    @Column(name = "illustration_name")
    private String name;
    @Enumerated(EnumType.STRING)
    private Language language;
    @Column(length = 1001)
    private String description;
    @Enumerated(EnumType.STRING)
    private Category category;
    @Column(name="creation_date")
    private LocalDate date ; 
    private String illustrationUrl;
    @ManyToOne
    @JoinColumn(name = "id_user")
    private _User user;
}
