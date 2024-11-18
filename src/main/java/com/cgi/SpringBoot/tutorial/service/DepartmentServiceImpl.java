package com.cgi.SpringBoot.tutorial.service;

import com.cgi.SpringBoot.tutorial.entity.Department;
import com.cgi.SpringBoot.tutorial.error.DepartmentNotFoundException;
import com.cgi.SpringBoot.tutorial.repository.DepartmentRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    Logger logger = LoggerFactory.getLogger(DepartmentServiceImpl.class);

    private final DepartmentRepo departmentRepo;

    // it is used to convert json to java object or vice versa
    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public DepartmentServiceImpl(DepartmentRepo departmentRepo){
        this.departmentRepo = departmentRepo;
    }

    @Override
    public Department saveDepartment(Department department) {
        return departmentRepo.save(department);
    }

    @Override
    public List<Department> fetchDepartmentList() throws Exception {
        List<Department> all = departmentRepo.findAll();
        //all.clear();
        if(all.isEmpty() || Objects.isNull(all)){
            throw new Exception("found data is empty");
        }
        //below logger is for
        //logger.info("returning list of departments {}",objectMapper.writeValueAsString(all));
        logger.info("returning list of departments {}",all);
        return all;
    }

    @Override
    public Department getDepartmentById(Long id) throws DepartmentNotFoundException {
        Optional<Department> department = departmentRepo.findById(id);

        if(!department.isPresent()){
            throw new DepartmentNotFoundException("no department found");
        }
        return department.get();
    }

    @Override
    public void deleteDepartmentById(Long departmentId) {
        departmentRepo.deleteById(departmentId);
    }

    @Override
    public Department updateDepartment(Long id, Department department) {
        Department depDB = departmentRepo.findById(id).get();
        if (Objects.nonNull(department.getDepartmentName()) &&
                !"".equalsIgnoreCase(department.getDepartmentName())) {
            depDB.setDepartmentName(department.getDepartmentName());

        }
        if(Objects.nonNull(department.getDepartmentCode()) &&
                !"".equalsIgnoreCase(department.getDepartmentCode())){
            depDB.setDepartmentName(department.getDepartmentCode());

        }
        if(Objects.nonNull(department.getDepartmentAddress()) &&
                !"".equalsIgnoreCase(department.getDepartmentAddress())){
            depDB.setDepartmentName(department.getDepartmentAddress());

        }
        return departmentRepo.save(depDB);
    }

    @Override
    public Department getDepartmentByName(String departmentName) {
        return departmentRepo.findByDepartmentNameIgnoreCase(departmentName);
        //refer the below link for more understanding of how to use custom jpa queries
        //https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html
    }

}
