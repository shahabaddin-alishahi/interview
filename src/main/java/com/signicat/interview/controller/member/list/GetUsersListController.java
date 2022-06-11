package com.signicat.interview.controller.member.list;



import com.signicat.interview.domain.Member;
import com.signicat.interview.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@PreAuthorize("hasAuthority('MEMBER_GET_LIST')")
@RequiredArgsConstructor
@RestController
public class GetUsersListController {

    private final MemberService memberService;

    @GetMapping("${apis.secure}/member")
    public UsersListResponse handle(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "nationalCode", required = false) String nationalCode,
            @RequestParam(name = "isWorking", required = false) Boolean isWorking,
            @RequestParam(name = "isEnabled", required = false) Boolean isEnabled,
            @RequestParam(name = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(name = "pageIndex", defaultValue = "0", required = false) int pageIndex) {
        Page<Member> users = memberService.findUsers(name, nationalCode, pageSize, pageIndex);

        List<UsersListResponse.UserResponse> items = users.getContent()
                .stream()
                .filter(
                        member -> (isWorking == null || member.getIsWorking().equals(isWorking)) &&
                                (isEnabled == null || member.getIsEnabled().equals(isEnabled))
                )
                .map(user ->
                        UsersListResponse.UserResponse.builder()
                                .id(String.valueOf(user.getId()))
                                .firstName(user.getFirstName())
                                .lastName(user.getLastName())
                                .username(user.getUsername())
                                .mobileNumber(user.getMobileNumber())
                                .nationalCode(user.getNationalCode())
                                .isWorking(user.getIsWorking())
                                .isEnabled(user.getIsEnabled())
                                .createDate(String.valueOf(user.getCreateDate()))
                                .updateDate(String.valueOf(user.getUpdateDate()))
                                .build())
                .collect(Collectors.toList());

        return UsersListResponse.builder()
                .items(items)
                .totalElements(users.getTotalElements() - (users.getTotalElements() - items.size()))
                .totalPages((long) users.getTotalPages())
                .build();
    }
}
