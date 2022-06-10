package com.signicat.interview.utils;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class AuditLogUtil {

  public final Pattern EXCLUDED_IP_ADDRESS_RANGE = Pattern.compile("^172\\.20\\.125\\.\\d+$");

    public String extractClientIpAddress(HttpServletRequest request) {
        if(request != null) {
            List<String> ipAddresses = new ArrayList<>();
            ipAddresses.addAll(Arrays.asList(request.getRemoteAddr().split(",")));
            if(request.getHeader("X-Forwarded-For") != null) {
                ipAddresses.addAll(Arrays.asList(request.getHeader("X-Forwarded-For").split(",")));
            }
            for(String ip : ipAddresses) {
                if(!EXCLUDED_IP_ADDRESS_RANGE.matcher(ip).matches()) {
                    return ip;
                }
            }
        }
        return null;

    }

}
