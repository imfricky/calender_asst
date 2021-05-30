package com.example.demo.service;

import com.example.demo.Repository.EmployeeRepository;
import com.example.demo.model.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeServiceTest {

    @Autowired
    EmployeeService employeeService;
    @MockBean
    EmployeeRepository employeeRepository;

    @Test
    public void shouldReturnTotalNoOfEmployeesFromGetAllEmployeeService(){
        when(employeeRepository.findAll()).thenReturn(Stream
                .of(new Employee(101L,"Test1","Test1@xyz"), new Employee(102L,"Test2","Test2@xyz")).collect(Collectors.toList()));
        assertEquals(2,employeeService.getAllEmployee().size());
        verify(employeeRepository).findAll();
    }

    @Test
    public void shouldReturnTotalNoOfEmployeesFromGetAllEmployeeServiceForEmployeesRemoval(){
        List<Employee> employeeList = new ArrayList<>();
        Employee employee1 = new Employee(101L,"Test1","Test1@xyz");
        Employee employee2 = new Employee(102L,"Test2","Test2@xyz");
        employeeList.add(employee1);
        employeeList.add(employee2);

        when(employeeRepository.findAll()).thenReturn(employeeList);
        assertEquals(2,employeeService.getAllEmployee().size());
        employeeList.remove(employee2);
        assertEquals(1,employeeService.getAllEmployee().size());
    }
    @Test
    public void shouldReturnTotalNoOfEmployeesFromGetAllEmployeeServiceForEmployeesAddition(){
        List<Employee> employeeList = new ArrayList<>();
        Employee employee1 = new Employee(101L,"Test1","Test1@xyz");
        Employee employee2 = new Employee(102L,"Test2","Test2@xyz");
        employeeList.add(employee1);
        employeeList.add(employee2);

        when(employeeRepository.findAll()).thenReturn(employeeList);
        assertEquals(2,employeeService.getAllEmployee().size());
        employeeList.add(new Employee(103L,"Test3","Test3@xyz"));
        assertEquals(3,employeeService.getAllEmployee().size());
    }

    @Test
    public void shouldReturnTotalNoOfEmployeesFromGetAllEmployeeServiceForNull(){
        when(employeeRepository.findAll()).thenReturn(null);
        assertNull(employeeService.getAllEmployee());
    }
    @Test
    public void shouldReturnTotalNoOfEmployeesFromGetAllEmployeeServiceForEmployeesAdditionAndRemovalSimultaneously(){
        List<Employee> employeeList = new ArrayList<>();
        Employee employee1 = new Employee(101L,"Test1","Test1@xyz");
        employeeList.add(employee1);
        when(employeeRepository.findAll()).thenReturn(employeeList);
        assertEquals(1,employeeService.getAllEmployee().size());
        employeeList.remove(employee1);
        assertEquals(0,employeeService.getAllEmployee().size());
    }

    @Test
    public void shouldReturnOneEmployeeForSaveEmployeeService(){
        Employee employee = new Employee(101L,"Test1","Test1@xyz");
        when(employeeRepository.save(employee)).thenReturn(employee);
        assertEquals(employee,employeeService.saveEmployee(employee));
    }

    @Test
    public void shouldReturnNullEmployeeForSaveEmployeeService(){
        Employee employee = new Employee(101L,"Test1","Test1@xyz");
        when(employeeRepository.save(null)).thenReturn(null);
        assertEquals(null,employeeService.saveEmployee(null));
    }

    @Test
    public void shouldReturnOneEmployeeForSaveEmployeeServiceForModifiedEmployee(){
        Employee employee = new Employee(101L,"Test1","Test1@xyz");
        when(employeeRepository.save(employee)).thenReturn(employee);
        employee.setName("TestRename");
        assertEquals(employee.getName(),employeeService.saveEmployee(employee).getName());
    }

    @Test
    public void shouldReturnEmployeeForSaveEmployeeServiceForSaveFetchModifyAgainSaveEmployee(){
        Employee employee = new Employee(101L,"Test1","Test1@xyz");
        when(employeeRepository.save(employee)).thenReturn(employee);
        Employee employee1 = employeeService.saveEmployee(employee);
        employee1.setName("Test Rename");
        assertEquals(employee.getId(),employeeService.saveEmployee(employee1).getId());
        System.out.println(employee.getId()+" "+employeeService.saveEmployee(employee1).getId());
    }

    @Test
    public void shouldReturnEmployeeForGetEmployeeServiceForCorrectId() {
        Long id = 101L;
        Optional<Employee> employee = Optional.of(new Employee(101L, "Rahul", "Rahul@xyz"));
        when(employeeRepository.findById(id)).thenReturn(employee);
        assertEquals(employee, employeeService.getEmployee(id));
    }

    @Test
    public void shouldReturnNullEmployeeForGetEmployeeServiceForNullId() {
        when(employeeRepository.findById(null)).thenReturn(null);
        assertEquals(null, employeeService.getEmployee(null));
    }

    @Test
    public void shouldReturnEmployeeForGetEmployeeServiceForNonExistingEmployee() {
        Long id = 102L;
        Optional<Employee> employee = Optional.of(new Employee(101L, "Rahul", "Rahul@xyz"));
        when(employeeRepository.findById(id)).thenReturn(null);
        assertEquals(null, employeeService.getEmployee(id));
    }

    @Test
    public void shouldReturnEmployeeForGetEmployeeServiceForNegativeId() {
        Long id = -102L;
        Optional<Employee> employee = Optional.of(new Employee(101L, "Rahul", "Rahul@xyz"));
        when(employeeRepository.findById(id)).thenReturn(null);
        assertEquals(null, employeeService.getEmployee(id));
    }

    @Test
    public void shouldReturnEmployeeForGetEmployeeServiceForModifiedEmployee() {
        Long id = 101L;
        Employee employee = new Employee(101L, "Rahul", "Rahul@xyz");
        when(employeeRepository.findById(id)).thenReturn(Optional.of(employee));
        employee.setEmail("Rahul@abc");
        assertEquals(employee.getEmail(), employeeService.getEmployee(id).get().getEmail());
        System.out.println(employee.getEmail()+" "+employeeService.getEmployee(id).get().getEmail());
    }

}
