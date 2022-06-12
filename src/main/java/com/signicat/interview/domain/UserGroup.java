package com.signicat.interview.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity(name = "USER_GROUP")
public class UserGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    @OneToMany(mappedBy = "userGroup", fetch = FetchType.EAGER)
    private Set<Member> members;
}
