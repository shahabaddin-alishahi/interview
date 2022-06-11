package com.signicat.interview.controller.authority.update;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Builder
@Setter
@Getter
public class UpdateAuthorityResponse {

    private String id;
    private String title;
    private String description;
    private String createDate;
    private String updateDate;

}
