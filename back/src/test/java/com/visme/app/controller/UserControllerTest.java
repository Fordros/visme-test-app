package com.visme.app.controller;

import com.visme.app.controller.utils.JsonUtil;
import com.visme.app.model.request.UserRequestDto;
import com.visme.app.model.response.UserResponseDto;
import com.visme.app.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@ExtendWith(SpringExtension.class)
//@WebMvcTest(UserControllerTest.class)
class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService service;

    @Test
    public void givenUsers_whenGetUsers_thenReturnJsonArray() throws Exception {

        UserResponseDto test = new UserResponseDto();
        test.setName("test");

        List<UserResponseDto> allUsers = Collections.singletonList(test);

        BDDMockito.given(service.getAllUsers()).willReturn(allUsers);

        mvc.perform(get("/api/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(test.getName())));
    }

    @Test
    void create() throws Exception {

        UserResponseDto test = new UserResponseDto();
        test.setName("Test");
        test.setEmail("test@test.com");
        test.setPassword("pass");

        given(service.create(Mockito.any())).willReturn(test);

        mvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJson(test)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is(test.getName())));

        verify(service, VerificationModeFactory.times(1)).create(Mockito.any());
        reset(service);
    }

    @Test
    void edit() {
    }

    @Test
    void delete() {
    }
}
