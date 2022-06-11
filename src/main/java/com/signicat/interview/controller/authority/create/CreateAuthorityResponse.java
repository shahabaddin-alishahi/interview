package com.signicat.interview.controller.authority.create;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Set;

@Builder
@Setter
@Getter
public class CreateAuthorityResponse {

    private String id;
    private String description;
    private String title;
    private String  createDate;
    private String updateDate;

}
