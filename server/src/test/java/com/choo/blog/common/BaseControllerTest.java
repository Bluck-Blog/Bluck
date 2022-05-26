package com.choo.blog.common;

import com.choo.blog.domain.users.User;
import com.choo.blog.domain.users.dto.SessionResponseData;
import com.choo.blog.domain.users.dto.UserLoginData;
import com.choo.blog.util.WebTokenUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Disabled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@Import(RestDocsConfiguration.class)
@ActiveProfiles("test")
@Transactional
@Rollback(value = true)
@Disabled
public class BaseControllerTest {
    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected UserProperties userProperties;

    @Autowired
    protected WebTokenUtil webTokenUtil;

    protected SessionResponseData session;

    protected User prepareUser(String suffix) throws Exception{
        MvcResult result = mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaTypes.HAL_JSON)
                .content(objectMapper.writeValueAsString(userProperties.prepareUserRegistData(suffix)))).andReturn();
        //String content = result.getResponse().getContentAsString();
        String content = getBody(result);
        return objectMapper.readValue(content, User.class);
    }

    private String getBody(MvcResult result) throws JSONException, UnsupportedEncodingException {
        JSONObject jsonObject = new JSONObject(result.getResponse().getContentAsString());
        String content = jsonObject.getString("body");
        return content;
    }

    protected SessionResponseData login(UserLoginData loginData) throws Exception{
        MvcResult result = mockMvc.perform(post("/api/session")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaTypes.HAL_JSON)
                .content(objectMapper.writeValueAsString(loginData))).andReturn();
        String content = result.getResponse().getContentAsString();
        System.out.println(content);
        return objectMapper.readValue(content, SessionResponseData.class);
    }

    protected UserLoginData prepareLoginData(){
        return UserLoginData.builder()
                .email(userProperties.getEmail())
                .password(userProperties.getPassword())
                .build();
    }
}
