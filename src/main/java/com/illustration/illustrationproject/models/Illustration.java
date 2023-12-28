package com.illustration.illustrationproject.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Illustration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_illus;
    @Column(name = "illustration_name")
    private String name;
    @Enumerated(EnumType.STRING)
    private Language language;
    @Column(length = 1001)
    private String description;
    @Column(name="creation_date")
    private LocalDate date ; 
    @Enumerated(EnumType.STRING)
    private Category category;
    private String emptyIllustrationUrl;
    private String illustrationUrl;
    private int status; // 0 deleted //active
    @ManyToOne
    @JoinColumn(name = "id_user")
    private _User user;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<AddedIllustration> addedIlustrtions;

}
