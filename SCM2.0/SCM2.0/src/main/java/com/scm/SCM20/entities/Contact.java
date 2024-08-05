package com.scm.SCM20.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter


public class Contact {

    @Id
    private String id;
    private String name;
    private String phoneNumber;
    private String email;
    private String  picture;
    private String  address;
    @Column(length = 1000)
    private String description;
    private  Boolean isFavourite = false;
    private String websiteLink;
    private String linkedInLink;

    @ManyToOne
    @JsonIgnore
    private User user;

    // many social links
    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, fetch = FetchType.EAGER , orphanRemoval = true)
    private List<SocialLinks> socialLinks = new ArrayList<SocialLinks>();

}
