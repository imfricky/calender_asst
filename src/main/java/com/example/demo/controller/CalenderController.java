package com.example.demo.controller;

import com.example.demo.Repository.CalenderRepository;
import com.example.demo.model.*;
import com.example.demo.model.TimeSlots;
import com.example.demo.service.CalenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Calendar;
import java.util.*;

@RestController
@RequestMapping(value= "/calender")
public class CalenderController {


    @Autowired
    CalenderService calenderService;

    @GetMapping("/getall")
    public List<Calender> getAll(){
        return calenderService.getAllCalender();
    }
    @GetMapping("/busyslots")
    public List<TimeSlots> getBusySlots(@RequestHeader String date,@RequestHeader Long id1, @RequestHeader Long id2)  {
        List<Calender> calender1 = calenderService.getEmployeeCalender(date,id1);
        List<Calender> calender2 = calenderService.getEmployeeCalender(date,id2);

        return calenderService.getBusyTimeSlots(calender1,calender2);
    }
    @GetMapping("/conflicts")
    public List<Employee> getConflicts(@RequestBody final Calender calender){
        System.out.println(calender.getDate()+calender.getStartTime()+calender.getEndTime());
        return calenderService.getConflictCalenderService(calender.getDate(),calender.getStartTime(),calender.getEndTime());

    }





}
