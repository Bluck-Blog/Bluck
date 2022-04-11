package com.choo.blog.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ErrorResponse {
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("errors")
    private List<CustomFieldError> customFieldErrors;

    static public ErrorResponse create() {
        return new ErrorResponse();
    }

    public ErrorResponse message(String message) {
        this.message = message;
        return this;
    }

    public ErrorResponse errors(Errors errors) {
        setCustomFieldErrors(errors.getFieldErrors());
        return this;
    }

    private void setCustomFieldErrors(List<FieldError> fieldErrors) {
        customFieldErrors = new ArrayList<>();

        fieldErrors.forEach(error -> {
            customFieldErrors.add(new CustomFieldError(
                    error.getObjectName(),
                    error.getCode(),
                    error.getRejectedValue()
            ));
        });
    }

    @Getter
    public static class CustomFieldError{
        private String objectName;
        private Object code;
        private String rejectedValue;

        public CustomFieldError(String objectName, String code, Object rejectedValue){
            this.objectName = objectName;
            this.code = code;
            if(rejectedValue != null)
                this.rejectedValue = rejectedValue.toString();
        }
    }
}
