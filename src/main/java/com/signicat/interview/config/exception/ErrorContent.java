package com.signicat.interview.config.exception;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorContent {
    private int code;
    private String message;
    private List<String> fields;
}
