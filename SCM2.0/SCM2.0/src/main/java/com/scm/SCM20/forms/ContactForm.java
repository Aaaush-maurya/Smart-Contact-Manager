package com.scm.SCM20.forms;

import jakarta.persistence.Column;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ContactForm {

    private String name;
    private String email;
    private String phoneNumber;
    private String  address;
    private  Boolean isFavourite;
    private String description;

    private String websiteLink;
    private String linkedInLink;

    private MultipartFile contactImage;


}
