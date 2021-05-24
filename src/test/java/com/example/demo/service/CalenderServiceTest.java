package com.example.demo.service;

import com.example.demo.Repository.CalenderRepository;
import com.example.demo.model.Calender;
import com.example.demo.model.Employee;
import com.example.demo.model.TimeSlots;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CalenderServiceTest {

    @Autowired
    CalenderService calenderService;
    @MockBean
    CalenderRepository calenderRepository;

    private Calender makeCalenderObject(Long mid, Long cid, String date, String startTime, String endTime) throws ParseException {
        Date date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(startTime);
        Date date2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endTime);
        Calender calender = new Calender(mid,cid,date,date1,date2);
        return calender;
    }

    @Test
    public void shouldGetAllTheCalendersFromGetAllCalenderTest() throws ParseException {
        //CalenderRepository calenderRepository = mock(CalenderRepository.class);
        Date date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-04-24 15:30:00");
        Date date2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-04-24 16:00:00");
        List <Calender> calenderList = new ArrayList<>();
        Calender c1 = new Calender(101L,10L, "24-04-2021",date1,date2);
        Calender c2 = new Calender(102L,11L, "24-04-2021",date1,date2);
        Calender c3 = new Calender(103L,12L, "24-04-2021",date1,date2);

        calenderList.add(c1);
        calenderList.add(c2);
        calenderList.add(c3);

        //CalenderServiceImpl calenderServiceImpl1 = new CalenderServiceImpl();
        when(calenderRepository.findAll()).thenReturn(calenderList);
        List<Calender> calenders = calenderService.getAllCalender();
        assertEquals(3,calenderService.getAllCalender().size());

    }

    @Test
    public void shouldGetEmployeeCalenderFromGetEmployeeCalenderTest() throws ParseException{
        Date date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-04-24 15:30:00");
        Date date2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-04-24 16:00:00");
        List <Calender> calenderList = new ArrayList<>();
        Calender c1 = new Calender(101L,10L, "24-04-2021",date1,date2);
        Calender c2 = new Calender(102L,11L, "24-04-2021",date1,date2);
        Calender c3 = new Calender(103L,12L, "24-04-2021",date1,date2);

        calenderList.add(c1);


        when(calenderRepository.getEmployeeCalender("24-04-2021",10L)).thenReturn(calenderList);

        List<Calender> calenderList1= calenderService.getEmployeeCalender("24-04-2021",10L);
        assertEquals(1,calenderList1.size());




    }

    @Test
    public void shouldGetNullFromGetEmployeeCalenderTestWithIDasNull() throws ParseException{
        Date date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-04-24 15:30:00");
        Date date2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-04-24 16:00:00");
        List <Calender> calenderList = new ArrayList<>();
        Calender c1 = new Calender(101L,10L, "24-04-2021",date1,date2);
        Calender c2 = new Calender(102L,11L, "24-04-2021",date1,date2);
        Calender c3 = new Calender(103L,12L, "24-04-2021",date1,date2);

        calenderList.add(c1);


        when(calenderRepository.getEmployeeCalender("24-04-2021",null)).thenReturn(null);

        List<Calender> calenderList2= calenderService.getEmployeeCalender("24-04-2021",null);
        assertEquals(null,calenderList2);


    }

    @Test
    public void shouldGetNullFromGetEmployeeCalenderTestWithDateasNull() throws ParseException{
        Date date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-04-24 15:30:00");
        Date date2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-04-24 16:00:00");
        List <Calender> calenderList = new ArrayList<>();
        Calender c1 = new Calender(101L,10L, "24-04-2021",date1,date2);
        Calender c2 = new Calender(102L,11L, "24-04-2021",date1,date2);
        Calender c3 = new Calender(103L,12L, "24-04-2021",date1,date2);

        calenderList.add(c1);


        when(calenderRepository.getEmployeeCalender(null,10L)).thenReturn(null);

        List<Calender> calenderList2= calenderService.getEmployeeCalender(null,10L);
        assertEquals(null,calenderList2);


    }
    @Test
    public void shouldGetNullFromGetEmployeeCalenderTestWithIDandDateasNull() throws ParseException{
        Date date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-04-24 15:30:00");
        Date date2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-04-24 16:00:00");
        List <Calender> calenderList = new ArrayList<>();
        Calender c1 = new Calender(101L,10L, "24-04-2021",date1,date2);
        Calender c2 = new Calender(102L,11L, "24-04-2021",date1,date2);
        Calender c3 = new Calender(103L,12L, "24-04-2021",date1,date2);

        calenderList.add(c1);


        when(calenderRepository.getEmployeeCalender(null,null)).thenReturn(null);

        List<Calender> calenderList2= calenderService.getEmployeeCalender(null,null);
        assertEquals(null,calenderList2);


    }

    @Test
    public void shouldGetTwoEmployeeCalenderFromGetEmployeeCalenderTest() throws ParseException{
        Date date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-04-24 15:30:00");
        Date date2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-04-24 16:00:00");
        List <Calender> calenderList = new ArrayList<>();
        Calender c1 = new Calender(101L,10L, "24-04-2021",date1,date2);
        Calender c2 = new Calender(102L,11L, "24-04-2021",date1,date2);
        Calender c3 = new Calender(103L,12L, "24-04-2021",date1,date2);
        Date date3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-04-24 17:30:00");
        Date date4 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-04-24 19:00:00");
        Calender c4 = new Calender(104L,10L, "24-04-2021",date1,date2);

        calenderList.add(c1);
        calenderList.add(c4);


        when(calenderRepository.getEmployeeCalender("24-04-2021",10L)).thenReturn(calenderList);

        List<Calender> calenderList2= calenderService.getEmployeeCalender("24-04-2021",10L);
        assertEquals(2,calenderList2.size());


    }

    @Test
    public void shouldGetCorrectTimeSlotsForNullCalenders() {
        List<Calender> calendar1 = Collections.emptyList();
        List<Calender> calendar2 = Collections.emptyList();
        List<TimeSlots> expectedTimeSlots = Collections.emptyList();

        List<TimeSlots> result = calenderService.getBusyTimeSlots(calendar1, calendar2);

        assertEquals(expectedTimeSlots, result);
    }
    @Test
    public void shouldGetCorrectTimeSlotsForFirstNullCalender() throws ParseException {
        List<Calender> calendar1 = new ArrayList<>();
        List<Calender> calendar2 = new ArrayList<>();
        List<TimeSlots> expectedTimeSlots = new ArrayList<>();
        //Calender calenderObject1 = new Calender();
        Calender calenderObject1 = makeCalenderObject(101L,10L, "24-04-2021","2021-04-24 17:30:00","2021-04-24 17:30:00");
        calendar1.add(calenderObject1);
        TimeSlots timeSlots = new TimeSlots(calenderObject1.getStartTime(),calenderObject1.getEndTime());
        expectedTimeSlots.add(timeSlots);
        List<TimeSlots> result = calenderService.getBusyTimeSlots(calendar1, calendar2);
        assertEquals(expectedTimeSlots.size(), result.size());
    }

    @Test
    public void shouldGetCorrectTimeSlotsForNotNullCalender() throws ParseException {
        List<Calender> calendar1 = new ArrayList<>();
        List<Calender> calendar2 = new ArrayList<>();
        List<TimeSlots> expectedTimeSlots = new ArrayList<>();
        //Calender calenderObject1 = new Calender();
        Calender calenderObject1 = makeCalenderObject(101L,10L, "24-04-2021","2021-04-24 15:30:00","2021-04-24 16:30:00");
        calendar1.add(calenderObject1);
        Calender calenderObject2 = makeCalenderObject(101L,10L, "24-04-2021","2021-04-24 17:30:00","2021-04-24 17:30:00");
        calendar2.add(calenderObject2);
        TimeSlots timeSlots1 = new TimeSlots(calenderObject1.getStartTime(),calenderObject1.getEndTime());
        TimeSlots timeSlots2 = new TimeSlots(calenderObject2.getStartTime(),calenderObject2.getEndTime());
        expectedTimeSlots.add(timeSlots1);
        expectedTimeSlots.add(timeSlots2);
        List<TimeSlots> result = calenderService.getBusyTimeSlots(calendar1, calendar2);
        assertEquals(expectedTimeSlots.size(), result.size());
    }

    @Test
    public void shouldGetCorrectTimeSlotsForSecondNullCalender() throws ParseException {
        List<Calender> calendar1 = new ArrayList<>();
        List<Calender> calendar2 = new ArrayList<>();
        List<TimeSlots> expectedTimeSlots = new ArrayList<>();
        //Calender calenderObject1 = new Calender();
        Calender calenderObject1 = makeCalenderObject(101L,10L, "24-04-2021","2021-04-24 17:30:00","2021-04-24 17:30:00");
        calendar2.add(calenderObject1);

        TimeSlots timeSlots = new TimeSlots(calenderObject1.getStartTime(),calenderObject1.getEndTime());
        expectedTimeSlots.add(timeSlots);
        List<TimeSlots> result = calenderService.getBusyTimeSlots(calendar1, calendar2);
        assertEquals(expectedTimeSlots.size(), result.size());
    }
    @Test
    public void shouldGetUserWhoHasConflictInCalenderFromGetConflictCalenderForParticularData() throws ParseException {
        Date date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-04-24 15:30:00");
        Date date2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-04-24 16:00:00");
        when(calenderRepository.getConflictCalender("24-04-2021",date1,date2)).thenReturn(Stream
                .of(101L,120L).collect(Collectors.toList()));
        assertEquals(2,calenderService.getConflictCalenderService("24-04-2021",date1,date2).size());
    }

    @Test
    public void shouldGetNullInCalenderFromGetConflictCalenderForNullEndTime() throws ParseException {
        List<Long> l1 = Collections.emptyList();
        List<Employee> employees = Collections.emptyList();
        Date date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-04-24 15:30:00");
        Date date2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-04-24 16:00:00");
        when(calenderRepository.getConflictCalender("24-04-2021",date1,null)).thenReturn(l1);
        assertEquals(employees,calenderService.getConflictCalenderService("24-04-2021",date1,null));
    }

    @Test
    public void shouldGetNullInCalenderFromGetConflictCalenderForNullStartTime() throws ParseException {
        List<Long> l1 = Collections.emptyList();
        List<Employee> employees = Collections.emptyList();
        Date date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-04-24 15:30:00");
        Date date2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-04-24 16:00:00");
        when(calenderRepository.getConflictCalender("24-04-2021",null,date2)).thenReturn(l1);
        assertEquals(employees,calenderService.getConflictCalenderService("24-04-2021",null,date2));
    }
    @Test
    public void shouldGetNullInCalenderFromGetConflictCalenderForNullDate() throws ParseException {
        List<Long> l1 = Collections.emptyList();
        List<Employee> employees = Collections.emptyList();
        Date date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-04-24 15:30:00");
        Date date2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-04-24 16:00:00");
        when(calenderRepository.getConflictCalender(null,date1,date2)).thenReturn(l1);
        assertEquals(employees,calenderService.getConflictCalenderService(null,date1,date2));
    }

    @Test
    public void shouldGetNullInCalenderFromGetConflictCalenderForNullStartTimeAndEndTime() throws ParseException {
        List<Long> l1 = Collections.emptyList();
        List<Employee> employees = Collections.emptyList();
        Date date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-04-24 15:30:00");
        Date date2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-04-24 16:00:00");
        when(calenderRepository.getConflictCalender("24-04-2021",null,null)).thenReturn(l1);
        assertEquals(employees,calenderService.getConflictCalenderService("24-04-2021",null,null));
    }


}
