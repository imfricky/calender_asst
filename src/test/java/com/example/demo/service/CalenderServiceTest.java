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

        List <Calender> calenderList = new ArrayList<>();
        calenderList.add(makeCalenderObject(101L,10L, "24-04-2021","2021-04-24 17:30:00","2021-04-24 18:30:00"));
        calenderList.add(makeCalenderObject(101L,10L, "24-04-2021","2021-04-24 19:30:00","2021-04-24 20:30:00"));
        calenderList.add(makeCalenderObject(101L,10L, "24-04-2021","2021-04-24 21:30:00","2021-04-24 22:30:00"));

        when(calenderRepository.findAll()).thenReturn(calenderList);
        List<Calender> calenders = calenderService.getAllCalender();
        assertEquals(3,calenderService.getAllCalender().size());

    }

    @Test
    public void shouldGetAllTheCalendersFromGetAllCalenderTestForEmptyCalender() throws ParseException{
        when(calenderRepository.findAll()).thenReturn(null);
        assertEquals(null,calenderService.getAllCalender());
    }

    @Test
    public void shouldGetAllTheCalendersFromGetAllCalenderTestForMultipleInsertion() throws ParseException{
        List <Calender> calenderList = new ArrayList<>();
        calenderList.add(makeCalenderObject(101L,10L, "24-04-2021","2021-04-24 17:30:00","2021-04-24 18:30:00"));
        calenderList.add(makeCalenderObject(101L,10L, "24-04-2021","2021-04-24 19:30:00","2021-04-24 20:30:00"));
        when(calenderRepository.findAll()).thenReturn(calenderList);
        assertEquals(2,calenderService.getAllCalender().size());
        calenderList.add(makeCalenderObject(101L,10L, "24-04-2021","2021-04-24 21:30:00","2021-04-24 22:30:00"));
        assertEquals(3,calenderService.getAllCalender().size());

    }
    @Test
    public void shouldGetAllTheCalendersFromGetAllCalenderTestForMultipleRemoval() throws ParseException{
        List <Calender> calenderList = new ArrayList<>();
        calenderList.add(makeCalenderObject(101L,10L, "24-04-2021","2021-04-24 17:30:00","2021-04-24 18:30:00"));
        calenderList.add(makeCalenderObject(101L,10L, "24-04-2021","2021-04-24 19:30:00","2021-04-24 20:30:00"));
        Calender calenderObject = makeCalenderObject(101L,10L, "24-04-2021","2021-04-24 21:30:00","2021-04-24 22:30:00");
        calenderList.add(calenderObject);
        when(calenderRepository.findAll()).thenReturn(calenderList);
        assertEquals(3,calenderService.getAllCalender().size());
        calenderList.remove(calenderObject);
        assertEquals(2,calenderService.getAllCalender().size());


    }

    @Test
    public void shouldGetAllTheCalendersFromGetAllCalenderTestForOneAdditionAndOneRemoval() throws ParseException{
        List <Calender> calenderList = new ArrayList<>();
        Calender calenderObject = makeCalenderObject(101L,10L, "24-04-2021","2021-04-24 21:30:00","2021-04-24 22:30:00");
        calenderList.add(calenderObject);
        when(calenderRepository.findAll()).thenReturn(calenderList);
        assertEquals(1,calenderService.getAllCalender().size());
        calenderList.remove(calenderObject);
        assertEquals(0,calenderService.getAllCalender().size());

    }

    @Test
    public void shouldGetEmployeeCalenderFromGetEmployeeCalenderTest() throws ParseException{
        List <Calender> calenderList = new ArrayList<>();
        calenderList.add(makeCalenderObject(101L,10L, "24-04-2021","2021-04-24 17:30:00","2021-04-24 18:30:00"));
        calenderList.add(makeCalenderObject(101L,10L, "24-04-2021","2021-04-24 19:30:00","2021-04-24 20:30:00"));
        when(calenderRepository.getEmployeeCalender("24-04-2021",10L)).thenReturn(calenderList);
        List<Calender> calenderList1= calenderService.getEmployeeCalender("24-04-2021",10L);
        assertEquals(2,calenderList1.size());
    }

    @Test
    public void shouldGetNullFromGetEmployeeCalenderTestWithIDasNull() throws ParseException{
        List <Calender> calenderList = new ArrayList<>();
        calenderList.add(makeCalenderObject(101L,10L, "24-04-2021","2021-04-24 17:30:00","2021-04-24 18:30:00"));
        when(calenderRepository.getEmployeeCalender("24-04-2021",null)).thenReturn(null);
        List<Calender> calenderList2= calenderService.getEmployeeCalender("24-04-2021",null);
        assertEquals(null,calenderList2);
    }

    @Test
    public void shouldGetNullFromGetEmployeeCalenderTestWithDateAsNull() throws ParseException{
        List <Calender> calenderList = new ArrayList<>();
        calenderList.add(makeCalenderObject(101L,10L, "24-04-2021","2021-04-24 17:30:00","2021-04-24 18:30:00"));
        when(calenderRepository.getEmployeeCalender(null,10L)).thenReturn(null);
        List<Calender> calenderList2= calenderService.getEmployeeCalender(null,10L);
        assertEquals(null,calenderList2);
    }

    @Test
    public void shouldGetNullFromGetEmployeeCalenderTestWithIdAndDateAsNull() throws ParseException{
        List <Calender> calenderList = new ArrayList<>();
        calenderList.add(makeCalenderObject(101L,10L, "24-04-2021","2021-04-24 17:30:00","2021-04-24 18:30:00"));
        when(calenderRepository.getEmployeeCalender(null,null)).thenReturn(null);
        List<Calender> calenderList2= calenderService.getEmployeeCalender(null,null);
        assertEquals(null,calenderList2);
    }

    @Test
    public void shouldGetEmployeeCalenderFromGetEmployeeCalenderTestForMultipleEmployeeCalender() throws ParseException{
        List <Calender> calenderList = new ArrayList<>();
        calenderList.add(makeCalenderObject(101L,10L, "24-04-2021","2021-04-24 17:30:00","2021-04-24 18:30:00"));
        calenderList.add(makeCalenderObject(101L,10L, "24-04-2021","2021-04-24 18:30:00","2021-04-24 19:30:00"));
        calenderList.add(makeCalenderObject(101L,10L, "24-04-2021","2021-04-24 19:30:00","2021-04-24 20:30:00"));
        when(calenderRepository.getEmployeeCalender("24-04-2021",10L)).thenReturn(calenderList);
        assertEquals(3,calenderService.getEmployeeCalender("24-04-2021",10L).size());


    }

    @Test
    public void shouldGetCorrectTimeSlotsForEmptyCalenders() {
        List<Calender> calendar1 = Collections.emptyList();
        List<Calender> calendar2 = Collections.emptyList();
        List<TimeSlots> expectedTimeSlots = Collections.emptyList();

        List<TimeSlots> result = calenderService.getBusyTimeSlots(calendar1, calendar2);

        assertEquals(expectedTimeSlots, result);
    }


    @Test
    public void shouldGetCorrectTimeSlotsForNonEmptyCalender() throws ParseException {
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
    public void shouldGetCorrectTimeSlotsForFirstEmptyCalender() throws ParseException {
        List<Calender> calendar1 = new ArrayList<>();
        List<Calender> calendar2 = new ArrayList<>();
        List<TimeSlots> expectedTimeSlots = new ArrayList<>();
        Calender calenderObject1 = makeCalenderObject(101L,10L, "24-04-2021","2021-04-24 17:30:00","2021-04-24 17:30:00");
        calendar1.add(calenderObject1);
        TimeSlots timeSlots = new TimeSlots(calenderObject1.getStartTime(),calenderObject1.getEndTime());
        expectedTimeSlots.add(timeSlots);
        List<TimeSlots> result = calenderService.getBusyTimeSlots(calendar1, calendar2);
        assertEquals(expectedTimeSlots.size(), result.size());
    }

    @Test
    public void shouldGetCorrectTimeSlotsForSecondEmptyCalender() throws ParseException {
        List<Calender> calendar1 = new ArrayList<>();
        List<Calender> calendar2 = new ArrayList<>();
        List<TimeSlots> expectedTimeSlots = new ArrayList<>();
        Calender calenderObject1 = makeCalenderObject(101L,10L, "24-04-2021","2021-04-24 17:30:00","2021-04-24 17:30:00");
        calendar2.add(calenderObject1);

        TimeSlots timeSlots = new TimeSlots(calenderObject1.getStartTime(),calenderObject1.getEndTime());
        expectedTimeSlots.add(timeSlots);
        List<TimeSlots> result = calenderService.getBusyTimeSlots(calendar1, calendar2);
        assertEquals(expectedTimeSlots.size(), result.size());
    }

    @Test
    public void shouldGetCorrectTimeSlotsForMultipleCalender() throws ParseException {
        List<Calender> calendar1 = new ArrayList<>();
        List<Calender> calendar2 = new ArrayList<>();
        List<TimeSlots> expectedTimeSlots = new ArrayList<>();

        Calender calenderObject1 = makeCalenderObject(101L,10L, "24-04-2021","2021-04-24 15:30:00","2021-04-24 16:30:00");
        Calender calenderObject2 = makeCalenderObject(101L,10L, "24-04-2021","2021-04-24 15:30:00","2021-04-24 16:30:00");
        Calender calenderObject3 = makeCalenderObject(101L,10L, "25-04-2021","2021-04-25 15:30:00","2021-04-25 16:30:00");
        calendar1.add(calenderObject1);
        calendar1.add(calenderObject2);
        calendar1.add(calenderObject3);

        Calender calenderObject4 = makeCalenderObject(101L,10L, "24-04-2021","2021-04-24 17:30:00","2021-04-24 17:30:00");
        Calender calenderObject5 = makeCalenderObject(101L,10L, "25-04-2021","2021-04-25 17:30:00","2021-04-25 17:30:00");
        Calender calenderObject6 = makeCalenderObject(101L,10L, "25-04-2021","2021-04-25 17:30:00","2021-04-25 17:30:00");
        calendar2.add(calenderObject4);
        calendar2.add(calenderObject5);
        calendar2.add(calenderObject6);

        TimeSlots timeSlots1 = new TimeSlots(calenderObject1.getStartTime(),calenderObject1.getEndTime());
        TimeSlots timeSlots2 = new TimeSlots(calenderObject2.getStartTime(),calenderObject2.getEndTime());
        TimeSlots timeSlots3 = new TimeSlots(calenderObject3.getStartTime(),calenderObject3.getEndTime());
        TimeSlots timeSlots4 = new TimeSlots(calenderObject4.getStartTime(),calenderObject4.getEndTime());
        TimeSlots timeSlots5 = new TimeSlots(calenderObject5.getStartTime(),calenderObject5.getEndTime());
        TimeSlots timeSlots6 = new TimeSlots(calenderObject6.getStartTime(),calenderObject6.getEndTime());
        expectedTimeSlots.add(timeSlots1);
        expectedTimeSlots.add(timeSlots2);
        expectedTimeSlots.add(timeSlots3);
        expectedTimeSlots.add(timeSlots4);
        expectedTimeSlots.add(timeSlots5);
        expectedTimeSlots.add(timeSlots6);

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
