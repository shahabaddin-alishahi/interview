package com.signicat.interview.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Data
@Entity
public class Authority implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String description;

    private String title;

    @Override
    public String getAuthority() {
        return title;
    }
}
