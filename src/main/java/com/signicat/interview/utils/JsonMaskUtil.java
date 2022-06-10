package com.signicat.interview.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class JsonMaskUtil {

    private final static Logger log = LoggerFactory.getLogger(JsonMaskUtil.class);
    private static Map<String, List<Pattern>> patternMap = new HashMap<>();

    private static List<Pattern> buildPattern(String field) {
        if (!patternMap.containsKey(field)) {
            patternMap.put(field, Arrays.asList(Pattern.compile(String.format("\"%s\"\\s*:\\s*(\"[^\"]*\")", field)),
                    Pattern.compile(String.format("\"%s\"\\s*:\\s*([^\"\\{\\},]*)", field))));
        }
        return patternMap.get(field);
    }

    public static String mask(String input, String[] fields) {
        try {
            for (String field : fields) {
                for (Pattern pattern : buildPattern(field)) {
                    Matcher matcher = pattern.matcher(input);
                    if (matcher.find()) {
                        String masked = "*".repeat(matcher.group(1).length());
                        input = matcher.replaceAll(String.format("\"%s\":\"%s\"", field, masked));
                        break;
                    }
                }

            }
        } catch (Exception e) {
            log.error("Error Masking " + input, e);
        }

        return input;
    }
}
