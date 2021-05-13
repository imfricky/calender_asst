package com.example.demo.controller;

import com.example.demo.Repository.CalenderRepository;
import com.example.demo.Repository.EmployeeRepository;
import com.example.demo.model.Calender;
import com.example.demo.model.Employee;
import com.example.demo.service.CalenderService;
import com.example.demo.service.EmployeeService;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CalenderControllerTest {

    @Autowired
    CalenderService calenderService;

    @MockBean
    CalenderRepository calenderRepository;

    @Test
    public void getAll() throws ParseException {


        Date date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-04-24 15:30:00");
        Date date2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-04-24 16:00:00");
        when(calenderRepository.findAll()).thenReturn(Stream
                .of(new Calender(1001L,101L,"20-02-2021",date1,date2)).collect(Collectors.toList()));
        assertEquals(1,calenderService.getAllCalender().size());
    }


    @Test
    public void getConflicts() throws ParseException {
        Date date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-04-24 15:30:00");
        Date date2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-04-24 16:00:00");
        when(calenderRepository.getConflictCalender("20-02-2021",date1,date2)).thenReturn(Stream
        .of(101L,120L).collect(Collectors.toList()));
        assertEquals(2,calenderService.getConflictCalenderService("20-02-2021",date1,date2).size());
    }
}