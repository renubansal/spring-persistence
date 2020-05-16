package com.example.persistence.controller;

import com.example.persistence.model.User;
import com.example.persistence.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserRepository repository;

    @Test
    public void shouldGetAllUsers() throws Exception {
        List<User> userList = new ArrayList<User>();
        userList.add(new User(1, "Eat", "Thrice"));
        userList.add(new User(2, "Sleep", "Twice"));

        when(repository.findAll()).thenReturn(userList);
        mockMvc.perform(get("/demo/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andDo(print());
    }

    @Test
    public void successfullyCreateToDo() throws Exception {
        User sampleToDo = new User(1, "Sample", "Todo");
        when(repository.save(any(User.class))).thenReturn(sampleToDo);

        ObjectMapper objectMapper = new ObjectMapper();
        String sampleToDoJson = objectMapper.writeValueAsString(sampleToDo);

        //Act
        ResultActions resultActions = mockMvc.perform(post("/demo/add").
                contentType(MediaType.APPLICATION_JSON).
                content(sampleToDoJson));

        //Assert
        resultActions.andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1));
    }
}