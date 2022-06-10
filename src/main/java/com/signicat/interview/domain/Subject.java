package com.signicat.interview.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
public class Subject {

    private Long id;

    private String username;

    private String password;

    private Set<UserGroup> groups;
}
