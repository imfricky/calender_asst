package com.example.demo.service;

import com.example.demo.Repository.CalenderRepository;
import com.example.demo.Repository.EmployeeRepository;
import com.example.demo.model.Calender;
import com.example.demo.model.Employee;
import com.example.demo.model.TimeSlots;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CalenderServiceImpl implements CalenderService{

    @Autowired
    CalenderRepository calenderRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public List<Calender> getAllCalender() {
        return calenderRepository.findAll();
    }

    @Override
    public List<Calender> getEmployeeCalender(String date,Long id) {
        return calenderRepository.getEmployeeCalender(date,id);
    }

    @Override
    public List<TimeSlots> getBusyTimeSlots(List<Calender> c1, List<Calender> c2) {
        List<Calender> calender1 = c1;
        List<Calender> calender2 = c2;
        List<TimeSlots> timeSlots = new ArrayList<>();

        for(int i=0;i<calender1.size();i++){
            Calender calender = new Calender();
            TimeSlots timeSlots1 = new TimeSlots();
            calender = calender1.get(i);
            timeSlots1.setStartTime(calender.getStartTime())
                    .setEndTime(calender.getEndTime());
            timeSlots.add(timeSlots1);
        }
        for(int i=0;i<calender2.size();i++){
            Calender calender = new Calender();
            TimeSlots timeSlots1 = new TimeSlots();
            calender = calender2.get(i);
            timeSlots1.setStartTime(calender.getStartTime())
                    .setEndTime(calender.getEndTime());
            timeSlots.add(timeSlots1);
        }
        Set<TimeSlots> uniqueTimeSlots = new HashSet<>(timeSlots);
        List<TimeSlots> listUniqueTimeSlots = new ArrayList<>(uniqueTimeSlots);
        System.out.println(listUniqueTimeSlots.size());
        //List<TimeSlots> listUniqueTimeSlots = timeSlots.stream().distinct().collect(Collectors.toList());
        return listUniqueTimeSlots;
    }

    @Override
    public List<Employee> getConflictCalenderService(String date, Date startTime, Date endTime) {
        List<Long> cid;
        cid = calenderRepository.getConflictCalender(date,startTime,endTime);
        List<Employee> employees = new ArrayList<>();

        for(int i=0;i<cid.size();i++){
            Employee employee = new Employee();
            System.out.println("Employee id : "+cid.get(i));
            Optional<Employee> employee1 = employeeRepository.findById(cid.get(i));

            if(employee1.isPresent()){
                employee = employee1.get();

            }

            Employee employee2 = new Employee(employee.getId(),employee.getName(),employee.getEmail());
            employees.add(employee2);


        }

        return employees;
    }
}
