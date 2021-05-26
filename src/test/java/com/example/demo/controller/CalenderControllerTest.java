package com.example.demo.controller;

import com.example.demo.Repository.CalenderRepository;
import com.example.demo.model.Calender;
import com.example.demo.service.CalenderService;
import org.junit.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.common.input.LineSeparatorDetector;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.w3c.dom.stylesheets.LinkStyle;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = CalenderController.class)
//@WebMvcTest(CalenderController.class)
public class CalenderControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
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
    public void test() throws Exception {
        List<Calender> calenderList = new ArrayList<>();
        calenderList.add(makeCalenderObject(101L,10L, "24-04-2021","2021-04-24 17:30:00","2021-04-24 18:30:00"));
        calenderList.add(makeCalenderObject(101L,10L, "24-04-2021","2021-04-24 19:30:00","2021-04-24 20:30:00"));
        calenderList.add(makeCalenderObject(101L,10L, "24-04-2021","2021-04-24 21:30:00","2021-04-24 22:30:00"));

        when(calenderService.getAllCalender()).thenReturn(calenderList);
        String url = "/calender-management/calenders";
        mockMvc.perform(get(url)).andExpect(status().isOk());

    }
    @Test
    public void test2() throws Exception {
        List<Calender> calenderList = new ArrayList<>();
        Calender calender1 = makeCalenderObject(101L,10L, "24-04-2021","2021-04-24 17:30:00","2021-04-24 18:30:00");
        calenderList.add(calender1);
        //calenderList.add(makeCalenderObject(101L,10L, "24-04-2021","2021-04-24 19:30:00","2021-04-24 20:30:00"));
        //calenderList.add(makeCalenderObject(101L,10L, "24-04-2021","2021-04-24 21:30:00","2021-04-24 22:30:00"));

        when(calenderService.getAllCalender()).thenReturn(calenderList);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/calender-management/calenders").accept(
                MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result.getResponse());
        Calender calenderExpected = calender1;
        JSONAssert.assertEquals(calenderExpected, result.getResponse());

    }

}