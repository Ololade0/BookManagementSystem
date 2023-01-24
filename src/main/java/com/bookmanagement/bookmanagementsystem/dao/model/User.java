package com.bookmanagement.bookmanagementsystem.dao.model;

import lombok.*;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Transactional
@Entity(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private String phonenumber;
    private String password;
    private boolean isEnabled;


    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Note> noteList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Role> roleHashSet = new HashSet<>();


    public User(String name, String email, String phonenumber, String password, RoleType roleType) {
        this.name = name;
        this.email = email;
        this.phonenumber = phonenumber;
        this.password = password;
        if (roleHashSet == null) {
            roleHashSet = new HashSet<>();
            roleHashSet.add(new Role(roleType));


        }
    }

    public User(String name) {
    }
}

