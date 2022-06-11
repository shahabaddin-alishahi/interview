package com.signicat.interview.controller.authority.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Builder
@Data
public class CreateAuthorityRequest {
    @NotBlank
    private String description;

    @NotBlank
    private String title;

}
