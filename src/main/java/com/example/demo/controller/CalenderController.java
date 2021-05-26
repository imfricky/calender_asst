package com.example.demo.controller;

import com.example.demo.model.Calender;
import com.example.demo.model.Employee;
import com.example.demo.model.TimeSlots;
import com.example.demo.service.CalenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value= "/calender-management")
public class CalenderController {


    @Autowired
    CalenderService calenderService;

    @GetMapping("/calenders")
    List<Calender> getAll(){
        return calenderService.getAllCalender();
    }

    @GetMapping("/busy-slots")
    List<TimeSlots> getBusySlots(@RequestParam String date, @RequestParam Long id1, @RequestParam Long id2)  {
        List<Calender> calender1 = calenderService.getEmployeeCalender(date,id1);
        List<Calender> calender2 = calenderService.getEmployeeCalender(date,id2);

        return calenderService.getBusyTimeSlots(calender1,calender2);
    }
    @GetMapping("/find-conflicts")
     List<Employee> getConflicts(@RequestBody final Calender calender){
        System.out.println(calender.getDate()+calender.getStartTime()+calender.getEndTime());
        return calenderService.getConflictCalenderService(calender.getDate(),calender.getStartTime(),calender.getEndTime());

    }





}
