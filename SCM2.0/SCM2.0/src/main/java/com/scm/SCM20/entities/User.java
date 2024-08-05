package com.scm.SCM20.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User implements UserDetails {

    @Id
    private String userId;
    private String password;
    @Column(nullable = false)
    private String email;
    @Column(name = "user_name",nullable = false)
    private String name;
    @Column(length = 1000)
    private String about;
    @Column(length = 300)
    private String profilePic;
    private String phoneNumber;

    //    verification
    private boolean emailVerified = false;
    private boolean phoneNumberVerified = false;
    private boolean enabled = true;

    //    provider
    @Enumerated(value = EnumType.STRING)
    private Providers provider = Providers.SELF;
    private String providerId;

    //    many contacts
    @OneToMany(mappedBy = "user" ,cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Contact> contacts = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roleList = new ArrayList<>();
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<SimpleGrantedAuthority> role = roleList.stream().map((roles)-> new SimpleGrantedAuthority(roles))
                .collect(Collectors.toList());
        return role;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}
