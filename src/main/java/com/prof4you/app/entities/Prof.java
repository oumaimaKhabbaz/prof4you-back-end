package com.prof4you.app.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "PROF")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"id"})
public class Prof {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<Subject> subjects =  new HashSet<>();

    @Column
    private Integer hourlyPrice;

    @ElementCollection
    private Set<String> Levels =  new HashSet<>();

    @ElementCollection
    private Set<String> teachMethod =  new HashSet<>();

    @ElementCollection
    private Set<String> lessonType =new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    private ProfResume profResume;

    @OneToOne(cascade = CascadeType.ALL)
    private Subscription subscription =  new Subscription();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();

    @Column
    private String city;






}
