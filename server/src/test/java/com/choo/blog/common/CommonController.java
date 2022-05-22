package com.choo.blog.common;

import com.choo.blog.exceptions.InvalidParameterException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/test")
public class CommonController {

    @PostMapping("/error")
    public void errorSample(@RequestBody @Valid SampleRequest dto, BindingResult result) {
        if(result.hasErrors()){
            throw new InvalidParameterException(result);
        }
    }
}
