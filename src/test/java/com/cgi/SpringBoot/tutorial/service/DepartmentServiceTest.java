package com.cgi.SpringBoot.tutorial.service;

import com.cgi.SpringBoot.tutorial.entity.Department;
import com.cgi.SpringBoot.tutorial.repository.DepartmentRepo;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

class DepartmentServiceTest {

    @Autowired
    private DepartmentService departmentService;

    @MockBean
    private DepartmentRepo departmentRepo;

    @BeforeEach
    void setUp() {
        Department  department = Department.builder().departmentId(1l)
                .departmentName("IT")
                .departmentAddress("Ahmedabad")
                .departmentCode("IT-06")
                .build();

        Mockito.when(departmentRepo.findByDepartmentNameIgnoreCase("IT")).
                thenReturn(department);
    }

    @Test
    @DisplayName("get data based on valid department name")
    @Disabled
    public void whenValidDepartName_thenDepartmentShouldFound() {
        String departmentname = "IT";
        Department found = departmentService.getDepartmentByName(departmentname);
        Assertions.assertEquals(departmentname,found.getDepartmentName());
    }


}