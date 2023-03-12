package com.example.board.common.extractor;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

import java.util.Enumeration;

@Component
public class AuthExtractor {

    public String extract(HttpServletRequest request, String type) {

        Enumeration<String> headers = request.getHeaders("Authorization");

        while (headers.hasMoreElements()) {
            String value = headers.nextElement();
            if (value.toLowerCase().startsWith(type.toLowerCase())) {
                return value.substring(type.length()).trim();
            }
        }

        return Strings.EMPTY;

    }

}
