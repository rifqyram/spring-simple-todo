package com.enigma.simpletodo.security.jwt;

import com.enigma.simpletodo.utils.WebResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

    private final Logger log = LoggerFactory.getLogger(AuthEntryPointJwt.class);

    private final ObjectMapper objectMapper;

    @Autowired
    public AuthEntryPointJwt(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        WebResponse<?> webResponse = new WebResponse<>(401, HttpStatus.UNAUTHORIZED, "Error: Unauthorized", null);
        String webResponseString = objectMapper.writeValueAsString(webResponse);

        log.error("Unauthorized error: {}", authException.getMessage());
        response.setContentType("application/json");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getWriter().write(webResponseString);
    }
}
