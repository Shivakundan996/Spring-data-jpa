package com.cgi.SpringBoot.tutorial.controller;

import com.cgi.SpringBoot.tutorial.entity.Department;
import com.cgi.SpringBoot.tutorial.error.DepartmentNotFoundException;
import com.cgi.SpringBoot.tutorial.service.DepartmentService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController  //(it is a combination of controller and
    // responsebody(it is used to deserialize json into java object) )
public class DepartmentController {

    @Autowired
    public DepartmentService departmentService;

    @Value("${welcome.message}")    // this is used to get values from properties file
    private String welcomeMessage;

    private final Logger logger = LoggerFactory.getLogger(DepartmentController.class);// if we class name in getLogger then in logs it will
    // show the in which class its getting used

    @PostMapping("/department")
    public ResponseEntity<Department> saveDepartment(@Valid @RequestBody Department department) {
        // RequestBody : taking json input from postman and deserializes it into java object

        /**  @some alternate ways for null checks
         * if (Objects.isNull(object)) {
         *     // Handle null case
         * }
         *
         * Optional<Object> optionalObject = Optional.ofNullable(object);
         *
         * if (optionalObject.isPresent()) {
         *
         * if (Objects.nonNull(object)) {
         *     // Proceed with object
         * }
         */

        if (department == null) {
            throw new NullPointerException("department object is null");
        } else {
            logger.error("inside saveDepartment of DepartmentController");
           return ResponseEntity.status(HttpStatus.OK).body(departmentService.saveDepartment(department));
//            return new ResponseEntity<>(departmentService.saveDepartment(department), HttpStatus.CREATED);
        }
    }

    @GetMapping("/departments")
    public List<Department> fetchDepartmentList() throws Exception {
        logger.error("inside fetchDepartment of DepartmentController");

        return departmentService.fetchDepartmentList();
    }

    @GetMapping("departments/{id}")
    Department getDepartmentById(@PathVariable("id") Long departmentId)
            throws DepartmentNotFoundException {
        return departmentService.getDepartmentById(departmentId);
    }

    @DeleteMapping("/departments/{id}")
    public String deleteDepartmentById(@PathVariable("id") Long departmentId) {
        departmentService.deleteDepartmentById(departmentId);
        logger.info("inside deleteDepartmentById of DepartmentController");
        return "department deleted successfully!";
    }

    @PutMapping("/departments/{id}")
    public Department updateDepartment(@PathVariable("id") Long id,
                                       @RequestBody Department department) {
        return departmentService.updateDepartment(id, department);

    }


    @GetMapping("/departments/name/{name}")
    public Department getDepartmentByName(@PathVariable("name") String departmentName) {
        return departmentService.getDepartmentByName(departmentName);

    }
}
