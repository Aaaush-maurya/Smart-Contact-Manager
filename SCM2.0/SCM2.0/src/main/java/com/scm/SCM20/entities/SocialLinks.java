package com.scm.SCM20.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SocialLinks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int social_id;
    private String link;
    private String title;

    @ManyToOne
    private Contact contact;
}
