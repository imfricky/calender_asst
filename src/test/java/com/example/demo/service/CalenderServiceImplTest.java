package com.example.demo.service;

import com.example.demo.Repository.CalenderRepository;
import com.example.demo.model.Calender;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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

import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CalenderServiceImplTest {

    @Autowired
    CalenderService calenderService;
    @MockBean
    CalenderRepository calenderRepository;

    @BeforeEach
    void setUp() {
        //MockitoAnnotations.initMocks(this);
        //calenderService = new CalenderService();
        //calenderService.setCalenderRepository(calenderRepository);

    }

    @AfterEach
    void tearDown() {
    }
    private Calender makeCalenderObject(Long mid, Long cid, String date, String startTime, String endTime) throws ParseException {
        Date date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(startTime);
        Date date2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endTime);
        Calender calender = new Calender(mid,cid,date,date1,date2);
        return calender;
    }

    @Test
    public void shouldGetEmployeeCalenderFromGetEmployeeCalenderTest() throws ParseException{
        List <Calender> calenderList = new ArrayList<>();
        calenderList.add(makeCalenderObject(101L,10L, "24-04-2021","2021-04-24 17:30:00","2021-04-24 18:30:00"));
        calenderList.add(makeCalenderObject(102L,10L, "24-04-2021","2021-04-24 19:30:00","2021-04-24 20:30:00"));

        when(calenderRepository.getEmployeeCalender("24-04-2021",10L)).thenReturn(calenderList);
        List<Calender> calenderList1= calenderService.getEmployeeCalender("24-04-2021",10L);

        Assertions.assertEquals(2,calenderList1.size());
    }
}