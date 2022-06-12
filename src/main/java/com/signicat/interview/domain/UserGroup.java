package com.signicat.interview.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity(name = "USER_GROUP")
public class UserGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
