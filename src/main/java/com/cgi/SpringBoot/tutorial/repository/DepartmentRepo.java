package com.cgi.SpringBoot.tutorial.repository;

import com.cgi.SpringBoot.tutorial.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepo extends JpaRepository<Department,Long> {

    public Department findByDepartmentNameIgnoreCase(String departmentName);
    public Department findByDepartmentName(String departmentName);

}
