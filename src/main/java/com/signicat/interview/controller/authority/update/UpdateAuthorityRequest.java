package com.signicat.interview.controller.authority.update;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Builder
@Data
public class UpdateAuthorityRequest {

    private String title;

    private String description;
}
