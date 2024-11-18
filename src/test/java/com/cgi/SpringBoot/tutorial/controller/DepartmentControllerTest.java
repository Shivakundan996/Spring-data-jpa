package com.cgi.SpringBoot.tutorial.controller;

import com.cgi.SpringBoot.tutorial.entity.Department;
import com.cgi.SpringBoot.tutorial.error.DepartmentNotFoundException;
import com.cgi.SpringBoot.tutorial.service.DepartmentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DepartmentController.class)
@ExtendWith(MockitoExtension.class)
class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentService departmentService;

    ObjectMapper objectMapper = new ObjectMapper();

    private Department department;

    @BeforeEach
    void setUp() {
        department = Department.builder()
                .departmentId(1L)
                .departmentAddress("Ahemdabad")
                .departmentName("IT")
                .departmentCode("IT-06")
                .build();

    }

    @Test
    void saveDepartment() throws Exception {
        Department inputdepartment = Department.builder()
                .departmentId(1L)
                .departmentAddress("Ahemdabad")
                .departmentName("IT")
                .departmentCode("IT-06")
                .build();

        Mockito.when(departmentService.saveDepartment(inputdepartment)).
                thenReturn(department);
        mockMvc.perform(get("/departments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputdepartment)))
                .andExpect(status().isOk());


    }

    @Test
    void getDepartmentById() throws Exception {
        // Arrange
        Department inputdepartment = Department.builder()
                .departmentId(1L)
                .departmentAddress("Ahemdabad")
                .departmentName("IT")
                .departmentCode("IT-06")
                .build();

        Mockito.when(departmentService.getDepartmentById(1L))
                .thenReturn(inputdepartment);

        // Act
        ResultActions perform = mockMvc.perform(
                get("/departments/1")
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // Assert
        perform.andExpect(status().isOk());
//                .andExpect(MockMvcResultMatchers.jsonPath("$.departmentName", CoreMatchers.is(inputdepartment.getDepartmentName())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.departmentAddress", CoreMatchers.is(inputdepartment.getDepartmentAddress())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.departmentCode", CoreMatchers.is(inputdepartment.getDepartmentCode())));
    }
}