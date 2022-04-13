package com.choo.blog.filter;

import com.choo.blog.error.ErrorResponse;
import com.choo.blog.exceptions.InvalidTokenException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.hql.internal.ast.ErrorReporter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.web.csrf.InvalidCsrfTokenException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class AuthenticationErrorFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        try{
            chain.doFilter(request, response);
        }
        catch (InvalidCsrfTokenException e){
            response.sendError(HttpStatus.UNAUTHORIZED.value());
        }
        catch (InvalidTokenException e){
            setErrorResponse(HttpStatus.UNAUTHORIZED, response, e);
        }
    }

    private void setErrorResponse(HttpStatus status, HttpServletResponse response, Exception ex) {
        response.setStatus(status.value());
        response.setContentType(MediaType.APPLICATION_JSON.getType());
        ErrorResponse error = ErrorResponse.create()
                .message(ex.getMessage());
    }
}
