package com.example.demo.service;

import com.example.demo.model.Calender;
import com.example.demo.model.Employee;
import com.example.demo.model.TimeSlots;

import java.util.Date;
import java.util.List;

public interface CalenderService {

    public List<Calender> getAllCalender();

    public List<Calender> getEmployeeCalender(String date,Long id);

    public List<TimeSlots> getBusyTimeSlots(List<Calender> c1, List <Calender> c2);

    public List<Employee> getConflictCalenderService(String date, Date startTime, Date endTime);

}
