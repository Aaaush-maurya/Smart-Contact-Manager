package com.scm.SCM20.dto;

import com.scm.SCM20.entities.User;
import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ContactDto {

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

}
