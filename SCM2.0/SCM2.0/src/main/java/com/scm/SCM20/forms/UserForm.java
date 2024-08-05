package com.scm.SCM20.forms;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserForm {
    private String password;
    private String email;
    private String name;
    private String about;
    private String phoneNumber;

}
