package com.choo.blog.security;

import com.choo.blog.error.ErrorResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomeBasicAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException ex) throws IOException {
        super.commence(request, response, ex);
        setResponse(HttpStatus.UNAUTHORIZED, response, ex);
    }

    @Override
    public void afterPropertiesSet() {
        super.setRealmName("choo");
        super.afterPropertiesSet();
    }

    private void setResponse(HttpStatus status, HttpServletResponse response, AuthenticationException ex){
        response.setContentType(MediaType.APPLICATION_JSON.toString());
        response.setStatus(status.value());
        ErrorResponse errorResponse = ErrorResponse.create()
                .message(ex.getMessage());
        try {
            String error = convertObjectToJson(errorResponse);
            response.getWriter().write(error);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String convertObjectToJson(Object errorResponse) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(errorResponse);
        return s;
    }
}
