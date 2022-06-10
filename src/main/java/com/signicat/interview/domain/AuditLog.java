package com.signicat.interview.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@Entity
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private LocalDateTime createDate;

    private String memberTitle;

    private Long memberId;

    private String requestUrl;

    private String actionMethod;

    private String actionObjectType;

    private String actionObjectId;

    private String responseStatusCode;

    @Lob
    private String responseBody;

}
