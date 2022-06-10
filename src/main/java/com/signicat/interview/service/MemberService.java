package com.signicat.interview.service;

import com.signicat.interview.domain.AuthenticationResult;
import com.signicat.interview.domain.Member;
import com.signicat.interview.domain.ProtectedValue;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Set;

public interface MemberService extends UserDetailsService {

    Member findByUserName(String username);

    Member findById(String id);

    Member findById(Long id);

    Page<Member> findUsers(String name, String nationalCode, int pageSize, int pageIndex);

    Member createUser(String username, String password, String firstName,
                      String lastName, String nationalCode, String mobileNumber, Boolean isEnabled,
                      Set<String> authorityIds);

    Member updateUser(String userId, String username, String password, String firstName,
                      String lastName, String nationalCode, String mobileNumber, Boolean isEnabled,
                      Set<String> authorityIds);

    AuthenticationResult login(String username, String password);

    AuthenticationResult refreshAccessToken(String refreshToken);

    Member loadUserByUsername(String username) throws UsernameNotFoundException;

    List<Member> getWorkingMembersList();

    void changePassword(Long id, ProtectedValue oldPassword, ProtectedValue newPassword, ProtectedValue confirmPassword);
}
