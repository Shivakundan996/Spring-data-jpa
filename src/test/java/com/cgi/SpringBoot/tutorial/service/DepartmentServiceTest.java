package com.cgi.SpringBoot.tutorial.service;

import com.cgi.SpringBoot.tutorial.entity.Department;
import com.cgi.SpringBoot.tutorial.error.DepartmentNotFoundException;
import com.cgi.SpringBoot.tutorial.repository.DepartmentRepo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {

 /*   @Autowired
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
    }*/
//
//
//    @Autowired
//    private DepartmentService departmentService;
//
//    @MockBean
//    private DepartmentRepo departmentRepo;

    @Mock
    private DepartmentRepo departmentRepo;

    @InjectMocks
    DepartmentServiceImpl departmentService;

    @Test
    @DisplayName("getting department by id")
    public void whenValidDepartName_thenDepartmentShouldFound() {
        Department department = new Department(2L,"IT","Bengaluru","2020");
        Optional<Department> department1 = Optional.of(department);
        when(departmentRepo.findById(2L)).thenReturn(department1);
        Department departmentById = departmentService.getDepartmentById(2L);
        assertEquals(departmentById,department);

    }


    @Test
    @DisplayName("to pass the if condition in getDepartmnetById")
    public void whenValidDepartName_thenDepartmentNotFound(){

        // arrange
        int id =2;

        //trying to cover if condiiton:

        /**  if(!department.isPresent()){
        //            throw new DepartmentNotFoundException("no department found");
            }*/

        //  checking for when department is present or not, so using below through making it empty
        //type 1
        when(departmentRepo.findById((long) id)).thenReturn(Optional.empty());

        //type 2
/*        when(departmentRepo.findById(1L))
                .thenThrow(new DepartmentNotFoundException("no department found"));*/
//        assertNotEquals(new Department(),departmentService.getDepartmentById(2L));

        //act & assert
        Exception exception = assertThrows(DepartmentNotFoundException.class,()->departmentService.getDepartmentById((long)id));
        assertEquals("no department found",exception.getMessage());

    }

    @Test
    public void fetchDepartmentList_test() throws Exception {
        List<Department> all = new ArrayList<>();
        //arrange
        Department dep1 = new Department(1L,"test","test","test");
        Department dep2 = new Department(1L,"test","test","test");
        all.add(dep1);all.add(dep2);

        //when(departmentRepo.findAll()).thenReturn(Arrays.asList(dep1,dep2));


        // code covergae for if condition (when list is empty)
        when(departmentRepo.findAll()).thenReturn(Collections.emptyList());
        Exception exception = assertThrows(Exception.class, () -> departmentService.fetchDepartmentList());
//        assertEquals("found data is empty", exception.getMessage());
    }

}