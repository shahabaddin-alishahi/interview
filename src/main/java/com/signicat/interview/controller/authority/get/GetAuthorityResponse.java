package com.signicat.interview.controller.authority.get;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GetAuthorityResponse {

    private String id;
    private String title;
    private String description;
    private String createDate;
    private String updateDate;
}
