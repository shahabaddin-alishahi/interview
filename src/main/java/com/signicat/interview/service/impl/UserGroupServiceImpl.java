package com.signicat.interview.service.impl;

import com.signicat.interview.domain.Authority;
import com.signicat.interview.domain.UserGroup;
import com.signicat.interview.exception.AuthorityNotFoundException;
import com.signicat.interview.exception.UserGroupNotFoundException;
import com.signicat.interview.repository.AuthorityRepository;
import com.signicat.interview.repository.UserGroupRepository;
import com.signicat.interview.service.AuthorityService;
import com.signicat.interview.service.UserGroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class UserGroupServiceImpl implements UserGroupService {

    private final UserGroupRepository userGroupRepository;


    @Override
    public UserGroup findById(String id) {
        return userGroupRepository.findById(Long.parseLong(id))
                .orElseThrow(UserGroupNotFoundException::new);
    }

    @Override
    public List<UserGroup> getAllUserGroupList() {
        return userGroupRepository.findAll();
    }

    @Override
    public UserGroup createUserGroup(String title) {
        return userGroupRepository.save(UserGroup.builder()
                        .title(title)
                        .createDate(LocalDateTime.now())
                        .updateDate(LocalDateTime.now())
                .build());
    }

    @Override
    public UserGroup updateUserGroup(String id, String title) {

        UserGroup userGroup = userGroupRepository.findById(Long.valueOf(id))
                .orElseThrow(UserGroupNotFoundException::new);

        userGroup.setTitle(title);
        userGroup.setUpdateDate(LocalDateTime.now());
        return userGroup;
    }
}
