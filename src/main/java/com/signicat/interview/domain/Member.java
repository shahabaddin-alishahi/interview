package com.signicat.interview.domain;

import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity(name = "MEMBER")
public class Member implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    @NonNull
    private String password;

    @NonNull
    private String nationalCode;

    @NonNull
    private String mobileNumber;

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    @Transient
    @Getter(value = AccessLevel.NONE)
    private String fullName;

    @NonNull
    private Boolean isEnabled;

    private Boolean isWorking;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "MEMBER_AUTHORITIES",
            joinColumns = @JoinColumn(name = "MEMBER_ID"),
            inverseJoinColumns = @JoinColumn(name = "AUTHORITY_ID"))
    private Set<Authority> authorities;

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

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

}
