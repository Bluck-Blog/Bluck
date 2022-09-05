package com.choo.blog.common;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class SampleRequest {
    @NotEmpty
    private String name;

    @Email
    private String email;

    public SampleRequest(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public SampleRequest() {
    }
}
