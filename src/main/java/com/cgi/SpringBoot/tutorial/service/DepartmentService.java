package com.cgi.SpringBoot.tutorial.service;

import com.cgi.SpringBoot.tutorial.entity.Department;
import com.cgi.SpringBoot.tutorial.error.DepartmentNotFoundException;

import java.util.List;


public interface DepartmentService {


    public Department saveDepartment(Department department);

    public  List<Department> fetchDepartmentList();

    public Department getDepartmentById(Long id) throws DepartmentNotFoundException;

    public void deleteDepartmentById(Long departmentId);

   public Department updateDepartment(Long id, Department department);

    Department getDepartmentByName(String departmentName);
}
