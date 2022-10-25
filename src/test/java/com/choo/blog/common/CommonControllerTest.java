package com.choo.blog.common;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CommonControllerTest extends BaseControllerTest{
    @Test
    public void errorSample() throws Exception {
        SampleRequest sampleRequest = new SampleRequest("name","hhh.naver");

        mockMvc.perform(
                        post("/test/error")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(sampleRequest))
                )
                .andExpect(status().isOk())
                .andDo(
                        document(
                                "error-response",
                                responseFields(
                                        fieldWithPath("message").description("에러 메시지"),
                                        fieldWithPath("errors").description("Error 값 배열 값"),
                                        fieldWithPath("errors[0].objectName").description("문제가 발생한 object"),
                                        fieldWithPath("errors[0].field").description("문제 있는 필드"),
                                        fieldWithPath("errors[0].rejectedValue").description("문제가 있는 값"),
                                        fieldWithPath("errors[0].code").description("error 코드")
                                )
                        )
                )
        ;
    }
}
