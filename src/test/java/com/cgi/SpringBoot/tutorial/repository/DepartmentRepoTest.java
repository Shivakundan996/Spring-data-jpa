package com.cgi.SpringBoot.tutorial.repository;

import com.cgi.SpringBoot.tutorial.entity.Department;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class DepartmentRepoTest {

    @Autowired
    private DepartmentRepo departmentRepo;

    @Autowired
    private EntityManager entityManager;

    @BeforeEach
    void setUp() {
        Department department = Department.builder()
                .departmentName("mechanical engineering")
                .departmentCode("ME - 011")
                .departmentAddress("delhi")
                .build();
        entityManager.persist(department);

    }

    @Test
    public void whenFindById_thenReturnDepartment(){
        Department department = departmentRepo.findById(1L).get();
        assertEquals(department.getDepartmentName(),"mechanical engineering");
    }

}