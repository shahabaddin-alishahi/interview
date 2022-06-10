package com.signicat.interview.config.audit;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.signicat.interview.config.security.OnlineUser;
import com.signicat.interview.domain.AuditLog;
import com.signicat.interview.service.AuditLogService;
import com.signicat.interview.utils.AuditLogUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;

import static java.nio.charset.StandardCharsets.UTF_8;

@RequiredArgsConstructor
@Component
@Slf4j
public class AuditLogFilter extends OncePerRequestFilter {

    private final AuditLogService auditLogService;
    private final AuditLogUtil auditLogUtil;
    private final OnlineUser onlineUser;

    private static JsonNode convertHttpServletResponseToJson(ContentCachingResponseWrapper responseWrapper) throws IOException {
        return new ObjectMapper().readTree(IOUtils.toString(responseWrapper.getContentInputStream(), String.valueOf(UTF_8)));
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        AuditLog auditLog = null;

        log.info("AuditLog Filter -> " + request.getRequestURI() + " From : " + auditLogUtil.extractClientIpAddress(request));
        if (Objects.nonNull(SecurityContextHolder.getContext().getAuthentication())) {
            String processedUrl = StringUtils.replace(request.getRequestURI(), "/api/v1", "")
                    .replace(request.getContextPath(), "");

            if (request.getMethod().equalsIgnoreCase("PUT")) {

                auditLog = AuditLog.builder()
                        .createDate(LocalDateTime.now())
                        .memberTitle(onlineUser.getTitle())
                        .memberId(onlineUser.getId())
                        .requestUrl(request.getRequestURL().toString())
                        .actionMethod(request.getMethod())
                        .actionObjectType(processedUrl.split("/")[1])
                        .actionObjectId(processedUrl.split("/")[2])
                        .build();

            }
            if (request.getMethod().equalsIgnoreCase("POST")) {

                auditLog = AuditLog.builder()
                        .createDate(LocalDateTime.now())
                        .memberTitle(onlineUser.getTitle())
                        .memberId(onlineUser.getId())
                        .requestUrl(request.getRequestURL().toString())
                        .actionMethod(request.getMethod())
                        .actionObjectType(processedUrl.split("/")[1])
                        .actionObjectId(StringUtils.EMPTY)
                        .build();
            }
        }

        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);

        filterChain.doFilter(request, responseWrapper);

        log.info("AuditLog Filter -> Done : " + request.getRequestURI());

        if (Objects.nonNull(auditLog)) {
            auditLog.setResponseBody(convertHttpServletResponseToJson(responseWrapper).toPrettyString());
            auditLog.setResponseStatusCode(String.valueOf(response.getStatus()));
            auditLogService.save(auditLog);
        }
        responseWrapper.copyBodyToResponse();
    }
}
