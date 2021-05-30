package com.example.demo.controller;

import com.example.demo.Repository.EmployeeRepository;
import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(value = EmployeeController.class)
public class EmployeeControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    EmployeeService employeeService;
    @MockBean
    EmployeeRepository employeeRepository;

    @Test
    public void shouldReturnStatusOkFromRestUrlEmployees() throws Exception {

        List<Employee> employeeList = new ArrayList<>();

        when(employeeService.getAllEmployee()).thenReturn(employeeList);
        String url = "/employee-management/employees";
        mockMvc.perform(get(url)).andExpect(status().isOk());

    }

    @Test
    public void shouldReturnOneEmployeesFromRestUrlEmployees() throws Exception {

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee(10L,"Test1","Test1@abc"));
        when(employeeService.getAllEmployee()).thenReturn(employeeList);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/employee-management/employees").accept(
                MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result.getResponse());
        String expected = "[{\"id\":10,\"name\":\"Test1\",\"email\":\"Test1@abc\",\"calender\":[]}]";
        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);



    }


    @Test
    public void shouldReturnOneEmployeesFromRestUrlEmployeesAfterRemoval() throws Exception {

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee(10L,"Test1","Test1@abc"));
        employeeList.add(new Employee(11L,"Test2","Test2@abc"));
        when(employeeService.getAllEmployee()).thenReturn(employeeList);
        employeeList.remove(1);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/employee-management/employees").accept(
                MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result.getResponse());
        String expected = "[{\"id\":10,\"name\":\"Test1\",\"email\":\"Test1@abc\",\"calender\":[]}]";
        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);



    }
    @Test
    public void shouldReturnNoEmployeesFromRestUrlEmployeesAfterRemoval() throws Exception {

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee(10L,"Test1","Test1@abc"));
        employeeList.add(new Employee(11L,"Test2","Test2@abc"));
        when(employeeService.getAllEmployee()).thenReturn(employeeList);
        employeeList.remove(0);
        employeeList.remove(0);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/employee-management/employees").accept(
                MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result.getResponse());
        String expected = "[]";
        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);



    }
    @Test
    public void shouldReturnEmployeesFromRestUrlEmployeesAfterAddition() throws Exception {

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee(10L,"Test1","Test1@abc"));
        employeeList.add(new Employee(11L,"Test2","Test2@abc"));
        when(employeeService.getAllEmployee()).thenReturn(employeeList);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/employee-management/employees").accept(
                MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result.getResponse());
        String expected = "[{\"id\":10,\"name\":\"Test1\",\"email\":\"Test1@abc\",\"calender\":[]},{\"id\":11,\"name\":\"Test2\",\"email\":\"Test2@abc\",\"calender\":[]}]";
        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
        employeeList.add(new Employee(12L,"Test3","Test3@abc"));
        result = mockMvc.perform(requestBuilder).andReturn();
        expected = "[{\"id\":10,\"name\":\"Test1\",\"email\":\"Test1@abc\",\"calender\":[]},{\"id\":11,\"name\":\"Test2\",\"email\":\"Test2@abc\",\"calender\":[]},{\"id\":12,\"name\":\"Test3\",\"email\":\"Test3@abc\",\"calender\":[]}]";
        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }

    @Test
    public void shouldReturnStatusOkFromRestUrlEmployeesId() throws Exception {
        Employee employee1 = new Employee(10L,"Test1","Test1@abc");
        when(employeeService.getEmployee(null)).thenReturn(null);
        String url = "/employee-management/employees/10";
        mockMvc.perform(get(url)).andExpect(status().isOk());
    }

    @Test
    public void shouldReturnOneEMployeeFromRestUrlEmployeesId() throws Exception {
        Employee employee1 = new Employee(10L,"Test1","Test1@abc");
        when(employeeService.getEmployee(10L)).thenReturn(Optional.of(employee1));
        String url = "/employee-management/employees/10";
        MvcResult result = mockMvc.perform(get(url)).andExpect(status().isOk()).andReturn();
        System.out.println(result.getResponse());
        String expected = "{\"id\":10,\"name\":\"Test1\",\"email\":\"Test1@abc\",\"calender\":[]}";
        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }

    @Test
    public void shouldReturnNullFromRestUrlEmployeesIdForNullId() throws Exception {
        Employee employee1 = new Employee(10L,"Test1","Test1@abc");
        when(employeeService.getEmployee(null)).thenReturn(null);
        String url = "/employee-management/employees/";
        MvcResult result = mockMvc.perform(get(url)).andExpect(status().isOk()).andReturn();
        String expected = "[]";
        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }
    @Test
    public void shouldReturnNullFromRestUrlEmployeesIdForNotPresentId() throws Exception {
        Employee employee1 = new Employee();
        when(employeeService.getEmployee(11L)).thenReturn(Optional.of(employee1));
        String url = "/employee-management/employees/11";
        MvcResult result = mockMvc.perform(get(url)).andExpect(status().isOk()).andReturn();
        String expected = "{\"id\":null,\"name\":null,\"email\":null,\"calender\":[]}";
        System.out.println("Result is : "+result.getResponse().getContentAsString());
        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }

    @Test
    public void shouldReturnStatusOkForSaveEmployee() throws Exception {
        Employee employee1 = new Employee(10L,"Test1","Test1@abc");
        when(employeeService.saveEmployee(employee1)).thenReturn(employee1);
        String url = "/employee-management/employees";
        mockMvc.perform(
                post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee1))
                .with(csrf())
        ).andExpect(status().isOk());

    }


    //These two functions gives JSON parsable Error.
    @Test
    public void shouldReturnEmployeeForSaveEmployee() throws Exception {
        Employee employee1 = new Employee(10L,"Test1","Test1@abc");
        when(employeeService.saveEmployee(employee1)).thenReturn(employee1);
        String url = "/employee-management/employees";
        MvcResult result =  mockMvc.perform(
                post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee1))
                        .with(csrf())
        ).andExpect(status().isOk()).andReturn();
        String expected = "[]";
        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }

    @Test
    public void shouldReturnEmployeeForSaveEmployee_1() throws Exception {
        Employee employee1 = new Employee(10L,"Test1","Test1@abc");
        doReturn(employee1).when(employeeService).saveEmployee(employee1);
        MvcResult result = mockMvc.perform(post("/employee-management/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee1))
                .with(csrf()))
                .andExpect(status().isOk())
                .andReturn();
        String expected = "{\"id\":10,\"name\":\"Test1\",\"email\":\"Test1@abc\",\"calender\":[]}";
        System.out.println(result.getResponse().getContentAsString());
        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }


}