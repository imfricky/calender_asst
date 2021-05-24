package com.example.demo.service;

import com.example.demo.Repository.CalenderRepository;
import com.example.demo.model.Calender;
import com.example.demo.model.TimeSlots;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CalenderServiceTest {

    @Autowired
    CalenderService calenderService;
    @MockBean
    CalenderRepository calenderRepository;


    @Test
    public void getAllCalenderTest1() throws ParseException {
        //CalenderRepository calenderRepository = mock(CalenderRepository.class);
        Date date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-04-24 15:30:00");
        Date date2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-04-24 16:00:00");
        List <Calender> calenderList = new ArrayList<>();
        Calender c1 = new Calender(101L,10L, "20-02-2021",date1,date2);
        Calender c2 = new Calender(102L,11L, "20-02-2021",date1,date2);
        Calender c3 = new Calender(103L,12L, "20-02-2021",date1,date2);

        calenderList.add(c1);
        calenderList.add(c2);
        calenderList.add(c3);

        //CalenderServiceImpl calenderServiceImpl1 = new CalenderServiceImpl();
        when(calenderRepository.findAll()).thenReturn(calenderList);
        List<Calender> calenders = calenderService.getAllCalender();
        assertEquals(3,calenderService.getAllCalender().size());

    }

    @Test
    public void getEmployeeCalenderTest1() throws ParseException{
        Date date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-04-24 15:30:00");
        Date date2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-04-24 16:00:00");
        List <Calender> calenderList = new ArrayList<>();
        Calender c1 = new Calender(101L,10L, "20-02-2021",date1,date2);
        Calender c2 = new Calender(102L,11L, "20-02-2021",date1,date2);
        Calender c3 = new Calender(103L,12L, "20-02-2021",date1,date2);

        calenderList.add(c1);


        when(calenderRepository.getEmployeeCalender("20-02-2021",10L)).thenReturn(calenderList);

        List<Calender> calenderList1= calenderService.getEmployeeCalender("20-02-2021",10L);
        assertEquals(1,calenderList1.size());

        when(calenderRepository.getEmployeeCalender("20-02-2021",null)).thenReturn(null);

        List<Calender> calenderList2= calenderService.getEmployeeCalender("20-02-2021",10L);
        assertEquals(0,calenderList2.size());


    }

    @Test
    public void getEmployeeCalenderTest2() throws ParseException{
        Date date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-04-24 15:30:00");
        Date date2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-04-24 16:00:00");
        List <Calender> calenderList = new ArrayList<>();
        Calender c1 = new Calender(101L,10L, "20-02-2021",date1,date2);
        Calender c2 = new Calender(102L,11L, "20-02-2021",date1,date2);
        Calender c3 = new Calender(103L,12L, "20-02-2021",date1,date2);

        calenderList.add(c1);


        when(calenderRepository.getEmployeeCalender("20-02-2021",null)).thenReturn(null);

        List<Calender> calenderList2= calenderService.getEmployeeCalender("20-02-2021",null);
        assertEquals(0,calenderList2.size());


    }

    @Test
    public void getEmployeeCalenderTest3() throws ParseException{
        Date date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-04-24 15:30:00");
        Date date2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-04-24 16:00:00");
        List <Calender> calenderList = new ArrayList<>();
        Calender c1 = new Calender(101L,10L, "20-02-2021",date1,date2);
        Calender c2 = new Calender(102L,11L, "20-02-2021",date1,date2);
        Calender c3 = new Calender(103L,12L, "20-02-2021",date1,date2);

        calenderList.add(c1);


        when(calenderRepository.getEmployeeCalender(null,10L)).thenReturn(null);

        List<Calender> calenderList2= calenderService.getEmployeeCalender("20-02-2021",10L);
        assertEquals(0,calenderList2.size());


    }

}
